package br.edu.ifrs.canoas.lds.webapp.domain;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.Base64Utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class File {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private String filename;
	@Column(length = 2147483647)
	private byte[] content;
	private String contentType;
	private Date createdOn;

	public String getPictureBase64() {
		return (content == null ? "/photos/no_image_available.png"
				: "data:image/png;base64," + Base64Utils.encodeToString(content));
	}

}
