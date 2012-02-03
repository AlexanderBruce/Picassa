package model.Expressions;

import java.util.Map;
import java.util.regex.Pattern;
import model.*;


public class VariableNameExpression extends Expression
{
    private static final Pattern MYKINDOFEXPRESSION =
        Pattern.compile("[a-z|A-Z]+");
    private String myVar;


    public VariableNameExpression (String variable)
    {
        myVar = variable;
    }


    private VariableNameExpression ()
    {

    }


    public String getMyVar ()
    {
        return myVar;
    }


    @Override
    public boolean isThisKindOfExpression (Parser parser)
    {
        parser.skipWhiteSpace();
        if (parser.checkPattern(MYKINDOFEXPRESSION))
        {

            return true;
        }

        return false;
    }


    @Override
    public Expression parseExpression (Parser parser)
    {
        parser.skipWhiteSpace();
        String variable = parser.grabExpressionKey(MYKINDOFEXPRESSION);
        parser.updatePosition(variable.length());
        return new VariableNameExpression(variable);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        if (variableNames.containsKey(myVar))
        {
            if (variableNames.get(myVar) instanceof VariableNameExpression)
            {
                VariableNameExpression errorCheck =
                    (VariableNameExpression) variableNames.get(myVar);
                if (errorCheck.getMyVar().equals(myVar))
                {
                    throw new ParserException("You mapped the expression to itself \nPlease try again");
                }
            }

            return variableNames.get(myVar).evaluate(x, y, variableNames);
        }
        else throw new ParserException("Undefined Variable");
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new VariableNameExpression());
    }

}
