/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author user
 */
public class ControllerFactory {

    private static String SSP_PLAYER = "player";
    private static String SSP_TEAM = "team";

    public static BaseController getServerApp(String name) {
        BaseController bsa = null;
        if (name.equalsIgnoreCase(SSP_PLAYER)) {
            bsa = new PlayerController();
        }
        else if (name.equalsIgnoreCase(SSP_TEAM)) {
            bsa = new TeamController();
        }
        return bsa;
    }
}
