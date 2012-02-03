package model.Expressions;

import java.util.*;
import java.util.regex.Pattern;
import model.Parser;
import model.ParserException;
import model.RGBColor;


/**
 * @author Alex Bruce
 * @author Mike Hewner
 */

public abstract class ParenExpression extends Expression
{

    protected static final Pattern MYKINDOFEXPRESSION =
        Pattern.compile("\\(([a-zA-Z\\!-\\%\\*\\-/\\+\\^]+)");

    private List<Expression> subExpressions;


    protected ParenExpression (List<Expression> subExpressions)
    {
        this.subExpressions = subExpressions;
    }


    /**
     * Java was implictly calling a unreachable superclass constructor so hizaah
     */
    protected ParenExpression ()
    {

    }


    protected List<Expression> getSubExpressions ()
    {
        return subExpressions;
    }


    protected List<RGBColor> evaluateSubExpressions (double x,
                                                     double y,
                                                     Map<String, Expression> variableNames)
    {
        
        
        List<RGBColor> result = new ArrayList<RGBColor>(subExpressions.size());
        for (Expression exp : subExpressions)
        {
            //to prevent subsequent calls from interfering with the original
            Map<String,Expression> currentVariables = new HashMap<String,Expression>();
            for(String key: variableNames.keySet()){
                currentVariables.put(key,variableNames.get(key));
            }
            
            result.add(exp.evaluate(x, y, currentVariables));
        }
        return result;
    }


    @Override
    public boolean isThisKindOfExpression (Parser parser)
    {
        parser.skipWhiteSpace();
        if (parser.checkPattern(MYKINDOFEXPRESSION))
        {

            String result = parser.grabExpressionKey(MYKINDOFEXPRESSION);
            for(String command:commandName())
            if (result.equals(command))
            {

                parser.updatePosition(result.length() + ONE);

                return true;
            }
        }
        return false;
    }


    @Override
    public Expression parseExpression (Parser parser)
    {
        List<Expression> subexpressions = new ArrayList<Expression>();
        for (int i = 0; i < numberOfParameters(); i++)
        {
            subexpressions.add(parser.parseExpression());
        }

        parser.skipWhiteSpace();
        if (parser.notAtEndOfString() && parser.currentCharacter() == ')')
        {
            parser.updatePosition(ONE);
            return constructParenExpression(subexpressions);
        }
        else
        {
            throw new ParserException("Expected close paren");
        }
    }


    protected abstract List<String> commandName ();


    protected abstract int numberOfParameters ();


    protected abstract ParenExpression constructParenExpression (List<Expression> subExpressions);


    @Override
    public abstract RGBColor evaluate (double x,
                                       double y,
                                       Map<String, Expression> variableNames);

}
