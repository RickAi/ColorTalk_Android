
package navyblue.top.colortalk.rest;

import navyblue.top.colortalk.rest.clients.ColorTalkClient;
import navyblue.top.colortalk.rest.services.ColorTalkService;

public class ServiceFactory {

    protected static final Object monitor = new Object();
    static ColorTalkService sColorTalkService = null;


    public static ColorTalkService getColorTalkSingleton() {
        synchronized (monitor) {
            if (sColorTalkService == null) {
                sColorTalkService = new ColorTalkClient().getColorTalkService();
            }
            return sColorTalkService;
        }
    }
}
