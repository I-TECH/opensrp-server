package org.opensrp.service.it;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opensrp.BaseIntegrationTest;
import org.opensrp.domain.Multimedia;
import org.opensrp.dto.form.MultimediaDTO;
import org.opensrp.repository.couch.MultimediaRepositoryImpl;
import org.opensrp.service.MultimediaService;
import org.opensrp.service.multimedia.BaseMultimediaFileManager;
import org.opensrp.service.multimedia.FileSystemMultimediaFileManager;
import org.opensrp.service.multimedia.MultimediaFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.opensrp.service.MultimediaService.IMAGES_DIR;
import static org.opensrp.util.SampleFullDomainObject.CASE_ID;
import static org.opensrp.util.SampleFullDomainObject.DIFFERENT_BASE_ENTITY_ID;
import static org.opensrp.util.SampleFullDomainObject.PROVIDER_ID;
import static org.opensrp.util.SampleFullDomainObject.getMultimedia;
import static org.opensrp.util.SampleFullDomainObject.getMultimediaDTO;
import static org.utils.AssertionUtil.assertTwoListAreSameIgnoringOrder;
import static org.utils.CouchDbAccessUtils.addObjectToRepository;

/**
 * If tests fail check property `'multimedia.directory.name` in `opensrp.properties`.
 */
public class MultimediaServiceTest extends BaseIntegrationTest {
	
	@Autowired
	private MultimediaRepositoryImpl multimediaRepository;

	@Autowired
	private MultimediaService multimediaService;
	
	@Value("#{opensrp['multimedia.directory.name']}")
	private String baseMultimediaDirPath;
	
	private String BASE_IMAGE_PATH = baseMultimediaDirPath + File.separator + IMAGES_DIR+ File.separator;

	@Autowired
	@Qualifier("multimedia_file_manager")
	private BaseMultimediaFileManager fileManager;

	@Before
	public void setUp() {
		multimediaRepository.removeAll();
		deleteFolders("../multimedia");
		fileManager = (BaseMultimediaFileManager) multimediaService.getFileManager();
		BASE_IMAGE_PATH = baseMultimediaDirPath + File.separator + IMAGES_DIR+ File.separator;
	}
	
	@After
	public void cleanUp() {
		multimediaRepository.removeAll();
		deleteFolders("../multimedia");
	}
	
	public void deleteFolders(String path) {
		File multimediaFile = new File(path);
		if (multimediaFile.exists()) {
			String[] entries = multimediaFile.list();
			for (String s : entries) {
				File currentFile = new File(multimediaFile.getPath(), s);
				if (currentFile.isDirectory()) {
					deleteFolders(currentFile.getPath());
				}
			}
			multimediaFile.delete();
		}
	}
	
	@Test
	public void shouldFindByCaseId() {
		Multimedia expectedMultimedia = getMultimedia();
		Multimedia invalidMultimedia = getMultimedia();
		invalidMultimedia.setCaseId(DIFFERENT_BASE_ENTITY_ID);
		addObjectToRepository(asList(expectedMultimedia, invalidMultimedia), multimediaRepository);
		
		Multimedia actualMultimedia = multimediaService.findByCaseId(CASE_ID);
		
		assertEquals(expectedMultimedia, actualMultimedia);
	}
	
	@Test
	public void shouldFindAllByProviderId() {
		Multimedia expectedMultimedia = getMultimedia();
		Multimedia expectedMultimedia1 = getMultimedia();
		List<Multimedia> expectedMultimedias = asList(expectedMultimedia, expectedMultimedia1);
		Multimedia invalidMultimedia = getMultimedia();
		invalidMultimedia.setProviderId(DIFFERENT_BASE_ENTITY_ID);
		addObjectToRepository(asList(expectedMultimedia, expectedMultimedia1, invalidMultimedia), multimediaRepository);
		
		List<Multimedia> actualMultimedias = multimediaService.getMultimediaFiles(PROVIDER_ID);
		
		assertTwoListAreSameIgnoringOrder(expectedMultimedias, actualMultimedias);
	}
	
	@Test
	public void shouldUploadJpegFile() throws IOException {
		String pathname = BASE_IMAGE_PATH + File.separator + CASE_ID + ".jpg";
		MultimediaDTO multimediaDTO = getMultimediaDTO("image/jpeg");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		Boolean result = fileManager.uploadFile(multimediaDTO, mockMultipartFile);
		
		assertTrue(result);
		assertTrue(new File(BASE_IMAGE_PATH).exists());
		verify(mockMultipartFile, times(1)).transferTo(new File(pathname));
	}
	
	@Test
	public void shouldUploadGifFile() throws IOException {
		String pathname = BASE_IMAGE_PATH + File.separator + CASE_ID + ".gif";
		MultimediaDTO multimediaDTO = getMultimediaDTO("image/gif");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		Boolean result = fileManager.uploadFile(multimediaDTO, mockMultipartFile);
		
		assertTrue(result);
		assertTrue(new File(BASE_IMAGE_PATH).exists());
		verify(mockMultipartFile, times(1)).transferTo(new File(pathname));
	}
	
	@Test
	public void shouldUploadPngFile() throws IOException {
		String pathname = BASE_IMAGE_PATH + CASE_ID + ".png";
		MultimediaDTO multimediaDTO = getMultimediaDTO("image/png");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		Boolean result = fileManager.uploadFile(multimediaDTO, mockMultipartFile);
		
		assertTrue(result);
		assertTrue(new File(BASE_IMAGE_PATH).exists());
		verify(mockMultipartFile, times(1)).transferTo(new File(pathname));
	}
	
	@Test
	public void shouldUploadVideoOctetStreamFile() throws IOException {
		String BASE_IMAGE_PATH = baseMultimediaDirPath + "/" + "videos/";
		String pathname = BASE_IMAGE_PATH + CASE_ID + ".mp4";
		MultimediaDTO multimediaDTO = getMultimediaDTO("application/octet-stream");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		Boolean result = fileManager.uploadFile(multimediaDTO, mockMultipartFile);
		
		assertTrue(result);
		assertTrue(new File(BASE_IMAGE_PATH).exists());
		verify(mockMultipartFile, times(1)).transferTo(new File(pathname));
	}
	
	@Test
	public void shouldReturnFalseUnknownContentType() throws IOException {
		MultimediaDTO multimediaDTO = getMultimediaDTO("unknown");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		assertFalse(fileManager.uploadFile(multimediaDTO, mockMultipartFile));
	}
	
	@Test
	public void shouldReturnFalseFormEmptyMultiPartFile() {
		MultimediaDTO multimediaDTO = getMultimediaDTO("image/jpeg");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		when(mockMultipartFile.isEmpty()).thenReturn(true);
		assertFalse(fileManager.uploadFile(multimediaDTO, mockMultipartFile));
	}
	
	@Test
	public void shouldSaveMultimediaForSuccessfulUpdate() {
		MultimediaDTO multimediaDTO = getMultimediaDTO("image/png");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		Multimedia expectedMultimedia = getMultimedia();
		expectedMultimedia.setContentType("image/png");
		expectedMultimedia.setFilePath(BASE_IMAGE_PATH + CASE_ID + ".png");
		
		String result = fileManager.saveMultimediaFile(multimediaDTO, mockMultipartFile);
		
		assertEquals("success", result);
		List<Multimedia> dbFiles = multimediaRepository.getAll();
		assertEquals(1, dbFiles.size());

		Multimedia actualMultimedia = dbFiles.get(0);
		assertEquals(expectedMultimedia.getFilePath(), actualMultimedia.getFilePath());
		assertEquals(expectedMultimedia.getProviderId(), actualMultimedia.getProviderId());
		assertEquals(expectedMultimedia.getContentType(), actualMultimedia.getContentType());
		assertEquals(expectedMultimedia.getFileCategory(), actualMultimedia.getFileCategory());
		assertEquals(expectedMultimedia.getCaseId(), actualMultimedia.getCaseId());
	}
	
	@Test
	public void shouldReturnFailForUnsuccessfulUpdate() {
		MultimediaDTO multimediaDTO = getMultimediaDTO("unknown");
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		
		String result = fileManager.saveMultimediaFile(multimediaDTO, mockMultipartFile);
		
		assertEquals("fail", result);
		List<Multimedia> dbFiles = multimediaRepository.getAll();
		assertEquals(0, dbFiles.size());
	}

	@Test
	public void testRetrieveFileShouldCallFileManager() {
		MultimediaFileManager fileManager = mock(FileSystemMultimediaFileManager.class);
		multimediaService.setFileManager(fileManager);
		multimediaService.retrieveFile("file_path");
		verify(fileManager).retrieveFile(eq("file_path"));
	}

	@Test
	public void testSaveFileShouldCallFileManager() {
		MultimediaFileManager fileManager = mock(FileSystemMultimediaFileManager.class);
		multimediaService.setFileManager(fileManager);

		MultipartFile multipartFile = mock(MultipartFile.class);
		MultimediaDTO multimediaDTO = mock(MultimediaDTO.class);
		multimediaService.saveFile(multimediaDTO, multipartFile);
		verify(fileManager).saveFile(eq(multimediaDTO), eq(multipartFile));
	}
}
