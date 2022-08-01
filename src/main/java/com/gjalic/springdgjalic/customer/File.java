package com.gjalic.springdgjalic.customer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String docName;
    private String docType;

    @Lob
    private byte[] data;

    protected File() {}
    public File(String docName, String docType, byte[] data) {
        this.ID = ID;
        this.docName = docName;
        this.docType = docType;
        this.data = data;
    }
}
