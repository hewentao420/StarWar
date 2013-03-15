package edu.toronto.ece1779.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import edu.toronto.ece1779.awsAccess.AwsAccessManager;
import edu.toronto.ece1779.ec2.entity.Image;
import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.service.ImageService;
import edu.toronto.ece1779.ec2.service.ImageServieImpl;
import edu.toronto.ece1779.ec2.service.ImageTransformation;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File fileTrans;
	private String filePath;
	private String filePathTrans;
	public static final String BUCKET_NAME = "group14_images";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			/*
			 * // Create a factory for disk-based file items FileItemFactory
			 * factory = new DiskFileItemFactory();
			 * 
			 * // Create a new file upload handler ServletFileUpload upload =
			 * new ServletFileUpload(factory);
			 * 
			 * // Parse the request List FileItem items =
			 * upload.parseRequest(request);
			 * 
			 * // User ID FileItem item1 = (FileItem) items.get(0);
			 * 
			 * String name = item1.getFieldName(); String userID =
			 * item1.getString();
			 * 
			 * // Uploaded File FileItem fileItem = (FileItem) items.get(1);
			 */

			MultiPartRequestWrapper request2 = (MultiPartRequestWrapper) request;
			File[] files = request2.getFiles("uploadedfile");
			File theFile = files[0];


			User user = new User();
			user.setId(Integer.parseInt(request.getParameter("userID")));

			// get root directory of web application
			String path = this.getServletContext().getRealPath("/");

			ImageService imageService = new ImageServieImpl();

			String originImageKey1 = "key1_" + UUID.randomUUID();
			String thumbnailKey2 = "key2_" + UUID.randomUUID();
			String transformedImageKey3 = "key3_" + UUID.randomUUID();
			String transformedImageKey4 = "key4_" + UUID.randomUUID();

			// get the file real path
			filePath = GetNewFileName(originImageKey1);
			filePathTrans = filePath + "_trans";

			// store file in server
			//File theFile = new File(filePath);
			//fileItem.write(theFile);

			// save first original image
			s3SaveFile(theFile, originImageKey1);

			// second image
			ImageTransformation.resizeImage(theFile.getPath(), filePathTrans);
			fileTrans = new File(filePathTrans);
			s3SaveFile(fileTrans, thumbnailKey2);
			fileTrans.delete();

			// third image
			ImageTransformation.blackAndWhite(theFile.getPath(), filePathTrans);
			fileTrans = new File(filePathTrans);
			s3SaveFile(fileTrans, transformedImageKey3);
			fileTrans.delete();

			// forth image
			ImageTransformation.addTextToImage(theFile.getPath(),
					filePathTrans, "Group 4 Logo");
			fileTrans = new File(filePathTrans);
			s3SaveFile(fileTrans, transformedImageKey4);
			fileTrans.delete();

			// add the image in db
			Image image = new Image(user.getId(), originImageKey1,
					thumbnailKey2, transformedImageKey3, transformedImageKey4);
			imageService.addImage(image);

			theFile.delete();

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void s3SaveFile(File file, String key) {
		BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance()
				.getAWSCredentials();

		AmazonS3 s3 = new AmazonS3Client(awsCredentials);
		String bucketName = BUCKET_NAME;
		s3.putObject(new PutObjectRequest(bucketName, key, file));
		s3.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
	}

	private String GetNewFileName(String saveName)
			throws FileNotFoundException, IOException {
		String savepath = "/tempUpload";// 构建图片保存的目录

		// 得到图片保存目录的真实路径
		String realsavepath = this.getServletContext().getRealPath(savepath);

		// 创建文件目录
		File logosavedir = new File(realsavepath);

		// 如果目录不存在就创建
		if (!logosavedir.exists()) {
			logosavedir.mkdirs();
		}

		System.out.println(realsavepath + "\\" + saveName);
		return realsavepath + "\\" + saveName;
	}

}
