package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorModel;


public class RgbToYCrCbExpression extends ParenExpression
{
    private RgbToYCrCbExpression ()
    {

    }


    public RgbToYCrCbExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }

    @Override
    protected List<String> commandName ()
    {
        List<String> rgbToYCrCb = new ArrayList<String>();
        rgbToYCrCb.add("rgbToYCrCb");
        return rgbToYCrCb;
    }
    



    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new RgbToYCrCbExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames);
        RGBColor toMake = ColorModel.rgb2ycrcb(results.get(0));
        return toMake;
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new RgbToYCrCbExpression());
    }

}
