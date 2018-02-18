package com.viniciusalvess.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "uploads")
public @Data class FileUpload {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="name")
	@NotNull
	@NotEmpty
	private String name;

	@Column(name="original_name")
	@NotNull
	@NotEmpty
	private String originalName;

	@Column(name="path")
	@NotNull
	@NotEmpty
	private String path;

	@Column(name="uploaded_at")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar uploadedAt;

	@PrePersist
	public void onCreate(){
		this.uploadedAt = Calendar.getInstance();
	}

	@Override
	public String toString(){
		SimpleDateFormat sf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a");
		return String.format("%06d - %s | %s", this.id, this.name,sf.format(this.uploadedAt.getTime()));
	}

	public String getNameForDownload(){
		return String.format("%08d_%s",this.getId(),this.name.replace(" ","_"));
	}
}
