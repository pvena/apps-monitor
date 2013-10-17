package com.example.loginuse.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

	public Compress(String[] files, String zipFile) {
		_files = files;
		_zipFile = zipFile;
	}

	/**
	 * Zip all files into a single zip file
	 */
	public void zip() {
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

			for (int i = 0; i < _files.length; i++) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
