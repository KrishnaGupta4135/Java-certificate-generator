/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projects.certificategenerator;

import java.util.ArrayList;


public class Validator {
    public static boolean validateCertificate(String id) {
        FileHandler handler= new FileHandler();
        ArrayList<String> idList= handler.getAllId();
        
        boolean valid= false;
        
        for(String s: idList) {
            if(id.equals(s)) {
                valid= true;
                break;
            }
        }
        
        return valid;
    }
}
