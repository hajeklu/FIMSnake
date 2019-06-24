package cz.uhk.fimsnake.activity.services;

/**
 * Created by Luboš Hájek on 24.06.2019.
 */
public class PreferencesService {

    private static boolean textures = true;

    public static boolean isTextures() {
        return textures;
    }

    public static void setTextures(boolean textures) {
        PreferencesService.textures = textures;
    }
}
