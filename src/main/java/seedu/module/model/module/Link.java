package seedu.module.model.module;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.validator.routines.UrlValidator;
import seedu.module.model.module.exceptions.LinkAccessException;

public class Link {
    public static final String MESSAGE_CONSTRAINTS = "Not a valid URL";

    public final String url;
    public final String name;

    public Link(String name, String url){
        requireNonNull(url);
        this.name = name;
        this.url = url;
    }

    public static boolean isValidUrl(String url){
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
    public void launch(){
        String runningOS = System.getProperty("os.name").toLowerCase();

        try {
            if(Desktop.isDesktopSupported()) { // Probably Windows
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(URI.create(url));
            } else { // Definitely Non-windows
                Runtime runtime = Runtime.getRuntime();
                if(runningOS.contains("mac")) { // Apples
                    runtime.exec("open " + url);
                }
                else if(runningOS.contains("nix") || runningOS.contains("nux")) { // Linux flavours
                    runtime.exec("xdg-open " + url);
                }
                else
                    throw new LinkAccessException("Unable/unwilling to launch a browser in your OS.");
            }
        }
        catch(IOException e) {
            throw new LinkAccessException("Couldn't open system browser: "+ e.getMessage());
        }
    }
}
