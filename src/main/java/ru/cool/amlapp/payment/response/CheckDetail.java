package ru.cool.amlapp.payment.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"isSuccessCheck"})
public class CheckDetail {

  @JsonProperty("checkType")
  private String checkType;

  @JsonProperty("isSuccessCheck")
  private boolean isSuccessCheck;

  @JsonProperty("description")
  private List<String> description;


}
