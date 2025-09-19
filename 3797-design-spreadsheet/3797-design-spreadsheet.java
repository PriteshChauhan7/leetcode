import java.util.*;

class Spreadsheet {
    private Map<String, Integer> cellValues;

    public Spreadsheet(int rows) {
        cellValues = new HashMap<>();
    }

    public void setCell(String cell, int value) {
        cellValues.put(cell, value);
    }

    public void resetCell(String cell) {
        cellValues.remove(cell);
    }

    public int getValue(String formula) {
        // formula format is something like "=A1+B2" or "=5+10" or "=A1+5"
        // remove "="
        String expr = formula.substring(1);
        int plusIdx = expr.indexOf('+');
        String part1 = expr.substring(0, plusIdx);
        String part2 = expr.substring(plusIdx + 1);

        return getOperandValue(part1) + getOperandValue(part2);
    }

    private int getOperandValue(String token) {
        if (Character.isDigit(token.charAt(0))) {
            return Integer.parseInt(token);
        } else {
            return cellValues.getOrDefault(token, 0);
        }
    }
}
