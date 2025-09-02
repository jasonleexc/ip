public class TestSplit {
    public static void main(String[] args) {
        String command = "  deadline  return book  ";
        String[] split = command.trim().split("\\s", 2);
        String firstWord = split[0];
        String description = split.length > 1 ? split[1] : "";
        
        System.out.println("Original: '" + command + "'");
        System.out.println("After trim: '" + command.trim() + "'");
        System.out.println("First word: '" + firstWord + "'");
        System.out.println("Description: '" + description + "'");
        System.out.println("Description length: " + description.length());
        
        // show each character in description
        System.out.print("Description chars: ");
        for (int i = 0; i < description.length(); i++) {
            char c = description.charAt(i);
            if (c == ' ') {
                System.out.print("[SPACE]");
            } else {
                System.out.print(c);
            }
        }
        System.out.println();
    }
}
