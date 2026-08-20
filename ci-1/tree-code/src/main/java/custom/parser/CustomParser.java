package custom.parser;

import java.awt.Color;
import java.io.IOException;
import java.io.Reader;

import lu.pcy113.pclib.PCUtils;

import custom.Parser;
import custom.lexer.CustomLexer;
import custom.lexer.CustomTokenTypes;
import custom.stmts.ColorStatement;
import custom.stmts.LoopStatement;
import custom.stmts.MoveStatement;
import custom.stmts.ParentStatement;
import custom.stmts.PenStateStatement;
import custom.stmts.ProgramStatement;
import custom.stmts.RotateStatement;
import custom.stmts.ThicknessStatement;
import l3.lexer.LexerException;
import l3.lexer.TokenTypes;
import l3.parser.ParserException;
import l3.tokens.StringLiteralToken;

public class CustomParser extends Parser {

	protected ParentStatement root = new ProgramStatement();

	public CustomParser(Reader reader) throws IOException, LexerException, ParserException {
		super(new CustomLexer(reader));
		// super.input.forEach(System.out::println);
		while (hasNext()) {
			parseStmt(root);
		}
	}

	private void parseStmt(ParentStatement parent) throws ParserException {
		if (peek(CustomTokenTypes.MOVE)) {
			parseMove(parent);
		} else if (peek(CustomTokenTypes.ROTATE)) {
			parseRotate(parent);
		} else if (peek(CustomTokenTypes.UP, CustomTokenTypes.DOWN)) {
			parseUpDown(parent);
		} else if (peek(CustomTokenTypes.LOOP)) {
			parseLoop(parent);
		} else if (peek(CustomTokenTypes.COLOR)) {
			parseColor(parent);
		} else if (peek(CustomTokenTypes.THICKNESS)) {
			parseThickness(parent);
		} else if (peek(TokenTypes.GOTO)) {
			parseGoto(parent);
		} else if (peek(TokenTypes.COMMENT)) {
			consume();
		} else {
			throw new UnsupportedOperationException("Unexpected token: " + peek());
		}
	}

	private void parseGoto(ParentStatement parent) throws ParserException {
		consume(TokenTypes.GOTO);
		parent.add(new GotoStatement(consumeNumericLiteral(), consumeNumericLiteral()));
	}

	private void parseThickness(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.THICKNESS);
		parent.add(new ThicknessStatement(consumeNumericLiteral()));
	}

	private void parseColor(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.COLOR);
		final Color color = parseColor();
		parent.add(new ColorStatement(color));
	}

	private Color parseColor() throws ParserException {
		if (peek(TokenTypes.STRING_LIT)) {
			return PCUtils.hexToColor(((StringLiteralToken) consume(TokenTypes.STRING_LIT)).getValue());
		} else {
			throw new UnsupportedOperationException(peek() + " " + peek(1));
		}
	}

	private void parseMove(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.MOVE);
		parent.add(new MoveStatement(consumeNumericLiteral()));
	}

	private void parseRotate(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.ROTATE);
		parent.add(new RotateStatement(consumeNumericLiteral()));
	}

	private void parseUpDown(ParentStatement parent) throws ParserException {
		parent.add(new PenStateStatement(
				consume(CustomTokenTypes.DOWN, CustomTokenTypes.UP).getType() == CustomTokenTypes.DOWN));
	}

	private void parseLoop(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.LOOP);
		final ParentStatement newParent = new LoopStatement(consumeNumericLiteral());
		consume(TokenTypes.CURLY_OPEN);
		while (!peek(TokenTypes.CURLY_CLOSE)) {
			parseStmt(newParent);
		}
		consume(TokenTypes.CURLY_CLOSE);
		parent.add(newParent);
	}

	public ParentStatement getRoot() {
		return root;
	}

}
