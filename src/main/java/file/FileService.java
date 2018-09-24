package file;

public interface FileService <T>{
	
	File readExternalToFile(T externalFile);
	T saveFileToExternalFile(File file);
}
