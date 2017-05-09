package io.sczyh30.pim.common.util;

/**
 * PIM service exception class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimServiceException extends Exception {

  private int statusCode;

  public PimServiceException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
