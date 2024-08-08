package com.scm.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ContactForm {
    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String description;

    private Boolean favorite;

    private String websiteLink;

    private String linkedInLink;

    private MultipartFile profileImage;

}
