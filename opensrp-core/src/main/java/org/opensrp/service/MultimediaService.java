package org.opensrp.service;

import java.io.File;
import java.util.List;

import org.opensrp.domain.Multimedia;
import org.opensrp.dto.form.MultimediaDTO;
import org.opensrp.repository.MultimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultimediaService {
<<<<<<< HEAD
	private static Logger logger = LoggerFactory.getLogger(MultimediaService.class
			.toString());
=======
    private static Logger logger = LoggerFactory.getLogger(MultimediaService.class.toString());
<<<<<<< HEAD
>>>>>>> 719c92e27338c071e47ad4e67af50a5a0b9402df

=======
    public static final String IMAGES_DIR="images";
    private static final String VIDEOS_DIR="videos";
>>>>>>> upstream/path
	private final MultimediaRepository multimediaRepository;
	private String multimediaDirPath;
	@Value("#{opensrp['multimedia.directory.name']}")
	String baseMultimediaDirPath;
	

	@Autowired
<<<<<<< HEAD
	public MultimediaService(MultimediaRepository multimediaRepository, @Value("#{opensrp['multimedia.directory.name']}") String multimediaDirName) {
=======
	public MultimediaService(MultimediaRepository multimediaRepository) {
>>>>>>> 719c92e27338c071e47ad4e67af50a5a0b9402df
		this.multimediaRepository = multimediaRepository;
		this.multimediaDirPath = multimediaDirName;
	}

	public String saveMultimediaFile(MultimediaDTO multimediaDTO, MultipartFile file) {
		
		boolean uploadStatus = uploadFile(multimediaDTO, file);
         
		String[] multimediaDirPathSplit =  multimediaDirPath.split("/", 3);
		String multimediaDirPathDB = File.separator + multimediaDirPathSplit[2];
		
		if (uploadStatus) {
			try {
				logger.info("Image path : " + multimediaDirPath);
				
				Multimedia multimediaFile = new Multimedia()
						.withCaseId(multimediaDTO.caseId())
						.withProviderId(multimediaDTO.providerId())
						.withContentType(multimediaDTO.contentType())
<<<<<<< HEAD
						.withFilePath(multimediaDirPathDB)
=======
						.withFilePath(multimediaDTO.filePath())
>>>>>>> 719c92e27338c071e47ad4e67af50a5a0b9402df
						.withFileCategory(multimediaDTO.fileCategory());

				multimediaRepository.add(multimediaFile);

				return "success";

			} catch (Exception e) {
				e.getMessage();
			}
		}

		return "fail";

	}

	public boolean uploadFile(MultimediaDTO multimediaDTO,
			MultipartFile multimediaFile) {
<<<<<<< HEAD
		 
		if (!multimediaFile.isEmpty()) {
			try {

				 multimediaDirPath += File.separator + multimediaDTO.providerId()+ File.separator;

				switch (multimediaDTO.contentType()) {
				
				case "application/octet-stream":
					String videoDirPath = multimediaDirPath += "videos";
					makeMultimediaDir(videoDirPath);
					multimediaDirPath += File.separator
							+ multimediaDTO.caseId() + ".mp4";
					break;

				case "image/jpeg":
					String jpgImgDirPath = multimediaDirPath += "images";
					makeMultimediaDir(jpgImgDirPath);
					multimediaDirPath += File.separator
							+ multimediaDTO.caseId() + ".jpg";
					break;

				case "image/gif":
					String gifImgDirPath = multimediaDirPath += "images";
					makeMultimediaDir(gifImgDirPath);
					multimediaDirPath += File.separator
							+ multimediaDTO.caseId() + ".gif";
					break;

				case "image/png":
					String pngImgDirPath = multimediaDirPath += "images";
					makeMultimediaDir(pngImgDirPath);
					multimediaDirPath += File.separator
							+ multimediaDTO.caseId() + ".png";
					break;

				default:
					String defaultDirPath = multimediaDirPath += "images";
					makeMultimediaDir(defaultDirPath);
					multimediaDirPath += File.separator
							+ multimediaDTO.caseId() + ".jpg";
=======
		
		// String baseMultimediaDirPath = System.getProperty("user.home");

		if (!multimediaFile.isEmpty()) {
			try {

				 multimediaDirPath = baseMultimediaDirPath + File.separator;
				String fileExt=".jpg";
				switch (multimediaDTO.contentType()) {
				
				case "application/octet-stream":
					multimediaDirPath += VIDEOS_DIR;
					fileExt=".mp4";
					break;

				case "image/jpeg":
					multimediaDirPath += IMAGES_DIR;
					fileExt=".jpg";
					break;

				case "image/gif":
					multimediaDirPath += IMAGES_DIR;
					fileExt=".gif";
					break;

				case "image/png":
					multimediaDirPath += IMAGES_DIR; 
					fileExt=".png";
>>>>>>> 719c92e27338c071e47ad4e67af50a5a0b9402df
					break;

				}
				new File(multimediaDirPath).mkdir();
				String fileName=multimediaDirPath+File.separator+multimediaDTO.caseId() + fileExt;
				multimediaDTO.withFilePath(fileName);
				File multimediaDir = new File(fileName);

				multimediaFile.transferTo(multimediaDir);

			/*
			 byte[] bytes = multimediaFile.getBytes();
			 	
			 BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(multimediaDirPath));
				stream.write(bytes);
				stream.close();*/
				 
				return true;
				
			} catch (Exception e) {
				logger.error("",e);
				return false;
			}
		} else {
			return false;
		}
	}
    private void makeMultimediaDir(String dirPath)
    {
    	File file = new File(dirPath);
		 if(!file.exists())
			 file.mkdirs();
			 
    }
	public List<Multimedia> getMultimediaFiles(String providerId) {
		return multimediaRepository.all(providerId);
	}
	public Multimedia findByCaseId(String entityId){
		return multimediaRepository.findByCaseId(entityId);
	}
}
