package vbsl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.naming.InvalidNameException;
import javax.swing.JOptionPane;

public class Command {

	protected final String cmd;
	protected final List<String> params;
	protected Command next;

	public Command(String l) {
		l = l.trim();
		final String[] tokens = l.split(" +");
		cmd = tokens[0];
		params = IntStream.range(1, tokens.length).mapToObj(i -> tokens[i]).collect(Collectors.toList());
	}

	public Command execute(final Program prog) throws InvalidNameException {
		if (cmd.startsWith("//") || cmd.isEmpty()) {
			return next;
		}

		if (!Language.isValid(this)) {
			throw new InvalidNameException("Invalid command: " + cmd);
		}

		switch (cmd.toLowerCase()) {
		case "jpd" -> {
			prog.jumpPoints.put(params.get(0), this);
		}
		case "def" -> {
			prog.variables.put(params.get(0), 0);
		}
		case "set" -> {
			if (!prog.variables.containsKey(params.get(0))) {
				throw new InvalidNameException("Undefined variable: " + params.get(0));
			}
			prog.variables.put(params.get(0), Integer.parseInt(params.get(1)));
		}
		case "dec" -> {
			if (!prog.variables.containsKey(params.get(0))) {
				throw new InvalidNameException("Undefined variable: " + params.get(0));
			}
			prog.variables.compute(params.get(0), (k, v) -> v - 1);
		}
		case "inc" -> {
			if (!prog.variables.containsKey(params.get(0))) {
				throw new InvalidNameException("Undefined variable: " + params.get(0));
			}
			prog.variables.compute(params.get(0), (k, v) -> v + 1);
		}
		case "jiz" -> {
			if (!prog.variables.containsKey(params.get(0))) {
				throw new InvalidNameException("Undefined variable: " + params.get(0));
			}
			if (!prog.jumpPoints.containsKey(params.get(1))) {
				throw new InvalidNameException("Undefined jumpoint: " + params.get(1));
			}
			if (prog.variables.get(params.get(0)) == 0) {
				return prog.jumpPoints.get(params.get(1));
			}
		}
		case "jnz" -> {
			if (!prog.variables.containsKey(params.get(0))) {
				throw new InvalidNameException("Undefined variable: " + params.get(0));
			}
			if (!prog.jumpPoints.containsKey(params.get(1))) {
				throw new InvalidNameException("Undefined jumpoint: " + params.get(1));
			}
			if (prog.variables.get(params.get(0)) != 0) {
				return prog.jumpPoints.get(params.get(1));
			}
		}
		case "out" -> {
			System.out.println(params.stream()
					.map(c -> prog.variables.containsKey(c) ? Integer.toString(prog.variables.get(c)) : c)
					.collect(Collectors.joining(" ")));
		}
		case "inp" -> {
			if (!prog.variables.containsKey(params.get(0))) {
				throw new InvalidNameException("Undefined variable: " + params.get(0));
			}
			final int value = Integer.parseInt(JOptionPane.showInputDialog("Input for '" + params.get(0) + "':"));
			prog.variables.put(params.get(0), value);
		}
		}

		return this.next;
	}

	public void setNext(final Command next) {
		this.next = next;
	}

	public Command getNext() {
		return this.next;
	}

	public String getCmd() {
		return cmd;
	}

	@Override
	public String toString() {
		return this.cmd + " " + String.join(" ", this.params);
	}

}
