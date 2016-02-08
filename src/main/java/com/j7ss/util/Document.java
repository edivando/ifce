package com.j7ss.util;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class Document implements Serializable, Comparable<Document>{
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
    private String name;
	@Getter @Setter
    private String size;
	@Getter @Setter 
    private String type;
     
    public Document(String name, String size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
	 
    public int compareTo(Document document) {
        return this.getName().compareTo(document.getName());
    }
}
