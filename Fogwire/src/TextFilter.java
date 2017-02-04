/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.io.*;

public class TextFilter extends javax.swing.filechooser.FileFilter  
{  
     public boolean accept(File file)  
     {  
          //Convert to lower case before checking extension  
         return (file.getName().toLowerCase().endsWith(".txt")  ||  
            file.isDirectory());
    }  
  
    public String getDescription()  
    {  
        return "Text File (*.txt)";  
    }  
} 