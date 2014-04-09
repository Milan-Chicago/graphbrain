package com.graphbrain.eco.nodes;

import com.graphbrain.eco.Context;
import com.graphbrain.eco.Contexts;
import com.graphbrain.eco.NodeType;

public class LenFun extends FunNode {

    public LenFun(ProgNode[] params, int lastTokenPos) {
        super(params, lastTokenPos);
    }

    public LenFun(ProgNode[] params) {
        this(params, -1);
    }

    @Override
    public String label(){return "len";}

    @Override
    public NodeType ntype(Context ctxt){return NodeType.Number;}

    @Override
    public void eval(Contexts ctxts) {
        ProgNode p = params[0];
        p.eval(ctxts);
        for (Context c : ctxts.getCtxts()) {
            int len = c.getRetWords(p).length();
            c.setRetNumber(this, len);
        }
    }
}