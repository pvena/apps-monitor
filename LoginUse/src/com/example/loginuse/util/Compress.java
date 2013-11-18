package com.example.loginuse.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.example.loginuse.log.LogConstants;

import android.os.Environment;
import android.util.Log;

/**
 * Create a zip file containing all zipping files
 * inside
 * 
 * @author Polo
 * 
 */
public class Compress {

	private static final int BUFFER = 2048;

	private String[] _files;
	private String _zipFile;
	private String _lastZip;

	public Compress(String dir, String zipFile) {
		_files = this.listFiles(dir);
		_zipFile = zipFile;
	}

	public boolean compressOK(){
		return (_lastZip != null);
	}
	/**
	 * Zip all files into a single zip file
	 */
	public void zip() {
		if(_files != null && _files.length > 0){
		try {
				File root = Environment.getExternalStorageDirectory();
				File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
				// have the object build the directory structure, if needed.
				directory.mkdirs();
         		File file = new File(directory,_zipFile);
         		if(!file.exists()) {	                        
              		file.createNewFile();
            	}
			
			
				BufferedInputStream origin = null;
				FileOutputStream dest = new FileOutputStream(file.getAbsolutePath());

				ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					dest));

				byte data[] = new byte[BUFFER];

				for (int i = 0; i < _files.length-1; i++) {
					String fileName = directory.getAbsolutePath()+"/"+_files[i];
					Log.v("Compress", "Adding: " + fileName);
					FileInputStream fi = new FileInputStream(fileName);
						origin = new BufferedInputStream(fi, BUFFER);
						ZipEntry entry = new ZipEntry(fileName.substring(fileName.lastIndexOf("/") + 1));
						out.putNextEntry(entry);
						int count;
						while ((count = origin.read(data, 0, BUFFER)) != -1) {
							out.write(data, 0, count);
						}
						origin.close();
				}

				out.close();
				_lastZip = file.getAbsolutePath();
			} 	
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			_lastZip = null;
	}
	
	private String[] listFiles(String dir) {
		File root = Environment.getExternalStorageDirectory();
		
		File directory = new File(root, dir);

		if (!directory.isDirectory()) {
			return null;
		}
		// create a FilenameFilter and override its accept-method
		FilenameFilter filefilter = new FilenameFilter() {

			public boolean accept(File dir, String name) {				
				return name.endsWith(".txt");
			}
		};

		return directory.list(filefilter);
	}
	
	public void deleteListFiles()
	{     	
		for (int i = 0; i < _files.length-1; i++) {
			this.deleteSingleFile(_files[i]);
		}
	}
	public void deleteLastZipFile()
	{
		this.deleteSingleFile(_lastZip);
	}
	private void deleteSingleFile(String path)
	{
		try		
		{		
			File root = Environment.getExternalStorageDirectory();
			File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
	    	// have the object build the directory structure, if needed.
	    	directory.mkdirs();    
         	File file = new File(directory.getAbsolutePath() + "/" + path);
         	file.delete();
		}
		catch(Exception ex)
		{
			Log.v("Compress", "Deleting File: " + path);
		}
	}
}
