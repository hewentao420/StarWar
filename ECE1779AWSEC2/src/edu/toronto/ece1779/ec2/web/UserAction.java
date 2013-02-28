package edu.toronto.ece1779.ec2.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import magick.MagickException;

import org.apache.struts2.ServletActionContext;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.awsAccess.AwsAccessManager;
import edu.toronto.ece1779.ec2.entity.Image;
import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.service.ImageService;
import edu.toronto.ece1779.ec2.service.ImageServieImpl;
import edu.toronto.ece1779.ec2.service.ImageTransformation;

public class UserAction extends ActionSupport {

	public static final String USER_AUTHENTICATE_FAILURE = "user_authenticate_failure";
	public static final String USER_AUTHENTICATE_SUCCESS = "user_authenticate_success";
	public static final String FILE_UPLOAD_SUCCESS = "file_upload_success";
	public static final String FILE_UPLOAD_FAILURE = "file_upload_failure";

	public static final String BUCKET_NAME = "group14_images";

	private static final long serialVersionUID = 1L;

	private File fileUpload;
	private File fileTrans;

	private String username;
	private List<Image> images;
	private Image image;

	private int imageId;
	private String filePath;
	private String filePathTrans;

	public String seeImage() {
		ImageService imageService = new ImageServieImpl();
		image = imageService.getImage(imageId);

		if (image == null) {
			return ERROR;
		}

		return SUCCESS;
	}

	public String enterUserUI() {
		@SuppressWarnings("unchecked")
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		ImageService imageService = new ImageServieImpl();

		User user = (User) session.get("user");
		String type = (String) session.get("type");

		if (user == null) {
			addActionError(getText("failure.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}

		if (!type.equals("user")) {
			addActionError(getText("failure.type.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}

		// update display data
		setUsername(user.getName());

		images = imageService.getUserImages(user.getId());

		return USER_AUTHENTICATE_SUCCESS;
	}

	public String uploadImage() {
		ImageService imageService = new ImageServieImpl();

		@SuppressWarnings("unchecked")
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		User user = (User) session.get("user");
		String type = (String) session.get("type");

		if (user == null) {
			addActionError(getText("failure.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}

		if (!type.equals("user")) {
			addActionError(getText("failure.type.authenticate"));
			return USER_AUTHENTICATE_FAILURE;
		}

		// check whether a file was uploaded
		if (fileUpload == null) {
			addActionError(getText("failure.no.file"));
			return FILE_UPLOAD_FAILURE;
		}

		// TODO:check whether the file is an image.

		String originImageKey1 = "key1_" + UUID.randomUUID();
		String thumbnailKey2 = "key2_" + UUID.randomUUID();
		String transformedImageKey3 = "key3_" + UUID.randomUUID();
		String transformedImageKey4 = "key4_" + UUID.randomUUID();

		// Save file from object to file
		try {
			filePath = GetNewFileName(originImageKey1);
			filePathTrans = filePath + "_trans";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// save first original image
		s3SaveFile(fileUpload, originImageKey1);

		// TODO: generate and save transformed images.
		try {
			// second image
			ImageTransformation
					.resizeImage(fileUpload.getPath(), filePathTrans);
			fileTrans = new File(filePathTrans);
			s3SaveFile(fileTrans, thumbnailKey2);
			fileTrans.delete();

			// third image
			ImageTransformation.blackAndWhite(fileUpload.getPath(), filePathTrans);
			fileTrans = new File(filePathTrans);
			s3SaveFile(fileTrans, transformedImageKey3);
			fileTrans.delete();

			// forth image
			ImageTransformation.addTextToImage(fileUpload.getPath(),
					filePathTrans, "Group 4 Logo");
			fileTrans = new File(filePathTrans);
			s3SaveFile(fileTrans, transformedImageKey4);
			fileTrans.delete();

		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// add the image in db
		Image image = new Image(user.getId(), originImageKey1, thumbnailKey2,
				transformedImageKey3, transformedImageKey4);
		imageService.addImage(image);

		return FILE_UPLOAD_SUCCESS;
	}

	private void s3SaveFile(File file, String key) {
		BasicAWSCredentials awsCredentials = AwsAccessManager.getInstance()
				.getAWSCredentials();

		AmazonS3 s3 = new AmazonS3Client(awsCredentials);
		String bucketName = BUCKET_NAME;
		s3.putObject(new PutObjectRequest(bucketName, key, file));
		s3.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
	}

	public static String GetNewFileName(String saveName)
			throws FileNotFoundException, IOException {
		String savepath = "/tempUpload";// 构建图片保存的目录

		// 得到图片保存目录的真实路径
		String realsavepath = ServletActionContext.getServletContext()
				.getRealPath(savepath);

		// 创建文件目录
		File logosavedir = new File(realsavepath);

		// 如果目录不存在就创建
		if (!logosavedir.exists()) {
			logosavedir.mkdirs();
		}

		System.out.println(realsavepath + "\\" + saveName);
		return realsavepath + "\\" + saveName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
