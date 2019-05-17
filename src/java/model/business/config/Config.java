package model.business.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 * Note that test mode parameters are hard coded for development so that a changed cfg file will
 * not screw things up.
 * 
 * @author Paul.Allen
 */
public class Config
{
    private ServletContext context = null;
    
    private final String cfgPath = "/etc/recomm.cfg";
    
    private boolean valid = false;
    
    
    /** Creates a new instance of Config
     * @param config */
    public Config (ServletContext context)
    {
        this.context = context;
        valid = parseCfgFile();
    }
    
    public void checkForUpdates()
    {
        if (!checkCfg())
        {
            updateCfg();
        }
        else
        {
            ;// Nothing to do - no changes indicated
        }
    }
    
    public void updateCfg()
    {
        valid = parseCfgFile();
    }


    /**
     * @return the valid
     */
    public boolean isValid()
    {
        return valid;
    }
    
    private boolean parseCfgFile()
    {
        boolean result = false;
        BufferedReader dataFile;
        String line;
        String[] parms;
        
        try 
        {
            dataFile = new BufferedReader (new FileReader (cfgPath));
            
            while ((line = dataFile.readLine ()) != null)
            {
                if (!line.startsWith ("//") && line.contains ("="))
                {
                    parms = line.split ("=");

                    switch (parms[0])
                    {         
                        case "valid":
                            if (parms[1].equals ("false"))
                            {
                                valid = false;
                            }
                            else
                            {
                                valid = true;
                            }
                            
                            break;

                        // Ignore everything else - it's an invlad parameter
                        default:
                            break;
                    }
                }
                else
                {
                    ;
                }
            }
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger (Config.class.getName()).log (Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger (Config.class.getName()).log (Level.SEVERE, null, ex);
        }
        
        return result;
    }

    private boolean checkCfg()
    {
        boolean result = false;
        BufferedReader dataFile;
        String line;
        String[] parms;
        
        try 
        {
            dataFile = new BufferedReader (new FileReader (cfgPath));
            
            while ((line = dataFile.readLine ()) != null)
            {
                if (!line.startsWith ("//") && line.contains ("="))
                {
                    parms = line.split ("=");

                    switch (parms[0])
                    {
                        case "valid":
                            if (parms[1].equals ("false"))
                            {
                                result = false;
                            }
                            else
                            {
                                result = true;
                            }
                            
                            break;

                        // Ignore everything else - it's an invlad parameter
                        default:
                            break;
                    }
                }
                else
                {
                    ;
                }
            }
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger (Config.class.getName()).log (Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger (Config.class.getName()).log (Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
