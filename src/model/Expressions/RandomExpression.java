package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.RGBColor;
import model.util.ColorCombinations;


public class RandomExpression extends ParenExpression
{

    public RandomExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }



    @Override
    protected List<String> commandName ()
    {
        List<String> random = new ArrayList<String>();
        random.add("random");
        return random;
    }


    private RandomExpression ()
    {

    }


    @Override
    protected int numberOfParameters ()
    {
        return 0;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new RandomExpression(subExpressions);
    }


    public static ExpressionFactory getFactory ()
    {

        return new ExpressionFactory(new RandomExpression());
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        Random generator = new Random();
        double red = ((generator.nextDouble() * 2) - 1);
        double green = ((generator.nextDouble() * 2) - 1);
        double blue = ((generator.nextDouble() * 2) - 1);
        RGBColor redPart = new RGBColor(red);
        RGBColor greenPart = new RGBColor(green);
        RGBColor bluePart = new RGBColor(blue);
        return ColorCombinations.color(redPart, greenPart, bluePart);
    }

}
