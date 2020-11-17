/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodies;

import model.CertificationType;
import model.Course;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Gubi
 */
@Converter(autoApply = true)
public class CertTypeConverter implements AttributeConverter<CertificationType, String>{

    @Override
    public String convertToDatabaseColumn(CertificationType attrib) {
        return attrib == null ? null : attrib.name();
    }

    @Override
    public CertificationType convertToEntityAttribute(String attrib) {
        return CertificationType.valueOf(attrib);
    }
   
    
}
