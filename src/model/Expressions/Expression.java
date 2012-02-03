package model.Expressions;

import java.util.Map;
import model.Parser;
import model.RGBColor;


/**
 * An Expression represents a mathematical expression as a tree. In this format,
 * the internal nodes represent mathematical functions and the leaves represent
 * constant values.
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public abstract class Expression
{
    protected static final int ONE = 1;


    public abstract boolean isThisKindOfExpression (Parser parser);


    public abstract Expression parseExpression (Parser parser);


    /**
     * @param time TODO
     * @param variables TODO
     * @return value of expression
     */
    public abstract RGBColor evaluate (double x,
                                       double y,
                                       Map<String, Expression> variableNames, double time);

}
