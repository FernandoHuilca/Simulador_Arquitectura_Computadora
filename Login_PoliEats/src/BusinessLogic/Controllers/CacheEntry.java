/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic.Controllers;

/**
 *
 * @author Fernando_Huilca
 */

public class CacheEntry {
    private String tag;
    private String nombreDelBloque;

    public CacheEntry(String tag, String nombreDelBloque) {
        this.tag = tag;
        this.nombreDelBloque = nombreDelBloque;
    }

    public String getTag() {
        return tag;
    }

    public String getNombreDelBloque() {
        return nombreDelBloque;
    }
    public String getBloqueContenido() {
        return nombreDelBloque;
    }

}
