package io.github.cepr0.demo.base.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseResponse implements Serializable {
	protected Integer id;
}
