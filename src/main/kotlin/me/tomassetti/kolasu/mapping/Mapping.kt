package me.tomassetti.kolasu.mapping

import me.tomassetti.kolasu.model.Node
import me.tomassetti.kolasu.model.Point
import me.tomassetti.kolasu.model.Position
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token

interface ParseTreeToAstMapper<in PTN : ParserRuleContext, out ASTN : Node> {
    fun map(parseTreeNode: PTN) : ASTN
}

fun Token.startPoint() = Point(line, charPositionInLine)

fun Token.endPoint() = Point(line, charPositionInLine + text.length)

fun ParserRuleContext.toPosition(considerPosition: Boolean) : Position? {
    return if (considerPosition && start!=null && stop!=null) {
        Position(start.startPoint(), stop.endPoint())
    } else null
}
