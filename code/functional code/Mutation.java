import java.util.Objects;

public class Mutation {
	int lineNumber;
	int tokenNumber;
	String origCode;
	String mutatedCode;

	Mutation(int l, int t, String o, String m) {
		lineNumber = l;
		tokenNumber = t;
		origCode = o;
		mutatedCode = m;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lineNumber, tokenNumber, origCode, mutatedCode);
	}

	@Override
	public boolean equals(Object o) {
		Mutation other = (Mutation)o;
		return this.lineNumber == other.lineNumber &&
				this.tokenNumber == other.tokenNumber &&
				this.origCode.equals(other.origCode) &&
				this.mutatedCode.equals(other.mutatedCode);
	}
	
	@Override
	public String toString() {
		return "Line " + lineNumber + ": " + origCode + " -> " + mutatedCode;
	}
}