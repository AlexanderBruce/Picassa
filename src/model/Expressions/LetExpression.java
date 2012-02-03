package model.Expressions;

import java.util.*;
import model.*;


public class LetExpression extends ParenExpression
{
    private LetExpression ()
    {

    }


    public LetExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> let = new ArrayList<String>();
        let.add("let");
        return let;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 3;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new LetExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {

        List<Expression> mySubExpressions = getSubExpressions();
        if (!(mySubExpressions.get(0) instanceof VariableNameExpression))
        {
            throw new ParserException("You redefined an existing command \nSuch as x or y");
        }
        VariableNameExpression key =
            (VariableNameExpression) mySubExpressions.get(0);
        variableNames.put(key.getMyVar(), mySubExpressions.get(1));
        return mySubExpressions.get(2).evaluate(x, y, variableNames);

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new LetExpression());
    }

}
