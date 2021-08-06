package forest;

import java.awt.event.MouseEvent;
import java.awt.Point;
import mvc.Controller;

/**
 * Forest Controller
 */
public class ForestController extends Controller
{
	/**
	 * construct
	 */
	public ForestController()
	{
		super();
	}

	/**
	 * getting str for clicking mouse
     * @param aMouseEvent mouse event
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
        Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		ForestModel aForestModel = (ForestModel)(this.model);
		aForestModel.mouseClicked(aPoint, aMouseEvent);
		return;
	}

    /**
	 * dragging mousse
     * @param aMouseEvent mouse event
	 */
    public void mouseDragged(MouseEvent aMouseEvent)
	{
		super.mouseDragged(aMouseEvent);
		return;
	}
}
