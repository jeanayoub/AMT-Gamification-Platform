/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import app.model.Badge;

/**
 *
 * @author marco
 */
public class BadgeDTO {
    private       long   ID;
    private       String name;
    private       String description;
    private       String icon;

    /**
     * This is the only constructor for a badge. It requires all the parameters.
     * 
     * @param id
     * @param name
     * @param description
     * @param icon
     */
    public BadgeDTO(Long ID, String name, String description, String icon) {
            this.ID          = ID;
            this.name        = name;
            this.description = description;
            this.icon        = icon;
    }

    public BadgeDTO(){

    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public String getIcon() {
            return icon;
    }

    public void setIcon(String icon) {
            this.icon = icon;
    }

    public long getID() {
            return ID;
    }
}
