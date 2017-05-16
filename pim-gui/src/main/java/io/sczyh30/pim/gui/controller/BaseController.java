package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.gui.PimServiceContext;

/**
 * Base UI controller.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public abstract class BaseController {

  protected final PimService service = PimServiceContext.getService();

  protected String getOwner() {
    return PimServiceContext.getContext().getOwner();
  }
}
