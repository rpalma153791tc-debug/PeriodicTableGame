import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class PeriodicTable {
    private static final Color BACKGROUND = new Color(13, 27, 42);
    private static final Color PANEL = new Color(19, 42, 62);
    private static final Color TEXT = new Color(231, 238, 245);
    private static final Color MUTED = new Color(127, 160, 189);
    private static final Color ACCENT = new Color(45, 212, 191);
    private static final String ELEMENT_DATA =
        """
        1|H|Hydrogen|1|1|nonmetal
        2|He|Helium|18|1|noble
        3|Li|Lithium|1|2|alkali
        4|Be|Beryllium|2|2|alkearth
        5|B|Boron|13|2|metalloid
        6|C|Carbon|14|2|nonmetal
        7|N|Nitrogen|15|2|nonmetal
        8|O|Oxygen|16|2|nonmetal
        9|F|Fluorine|17|2|halogen
        10|Ne|Neon|18|2|noble
        11|Na|Sodium|1|3|alkali
        12|Mg|Magnesium|2|3|alkearth
        13|Al|Aluminium|13|3|post
        14|Si|Silicon|14|3|metalloid
        15|P|Phosphorus|15|3|nonmetal
        16|S|Sulfur|16|3|nonmetal
        17|Cl|Chlorine|17|3|halogen
        18|Ar|Argon|18|3|noble
        19|K|Potassium|1|4|alkali
        20|Ca|Calcium|2|4|alkearth
        21|Sc|Scandium|3|4|transition
        22|Ti|Titanium|4|4|transition
        23|V|Vanadium|5|4|transition
        24|Cr|Chromium|6|4|transition
        25|Mn|Manganese|7|4|transition
        26|Fe|Iron|8|4|transition
        27|Co|Cobalt|9|4|transition
        28|Ni|Nickel|10|4|transition
        29|Cu|Copper|11|4|transition
        30|Zn|Zinc|12|4|transition
        31|Ga|Gallium|13|4|post
        32|Ge|Germanium|14|4|metalloid
        33|As|Arsenic|15|4|metalloid
        34|Se|Selenium|16|4|nonmetal
        35|Br|Bromine|17|4|halogen
        36|Kr|Krypton|18|4|noble
        37|Rb|Rubidium|1|5|alkali
        38|Sr|Strontium|2|5|alkearth
        39|Y|Yttrium|3|5|transition
        40|Zr|Zirconium|4|5|transition
        41|Nb|Niobium|5|5|transition
        42|Mo|Molybdenum|6|5|transition
        43|Tc|Technetium|7|5|transition
        44|Ru|Ruthenium|8|5|transition
        45|Rh|Rhodium|9|5|transition
        46|Pd|Palladium|10|5|transition
        47|Ag|Silver|11|5|transition
        48|Cd|Cadmium|12|5|transition
        49|In|Indium|13|5|post
        50|Sn|Tin|14|5|post
        51|Sb|Antimony|15|5|metalloid
        52|Te|Tellurium|16|5|metalloid
        53|I|Iodine|17|5|halogen
        54|Xe|Xenon|18|5|noble
        55|Cs|Cesium|1|6|alkali
        56|Ba|Barium|2|6|alkearth
        57|La|Lanthanum|3|6|lan
        58|Ce|Cerium|3|9|lan
        59|Pr|Praseodymium|4|9|lan
        60|Nd|Neodymium|5|9|lan
        61|Pm|Promethium|6|9|lan
        62|Sm|Samarium|7|9|lan
        63|Eu|Europium|8|9|lan
        64|Gd|Gadolinium|9|9|lan
        65|Tb|Terbium|10|9|lan
        66|Dy|Dysprosium|11|9|lan
        67|Ho|Holmium|12|9|lan
        68|Er|Erbium|13|9|lan
        69|Tm|Thulium|14|9|lan
        70|Yb|Ytterbium|15|9|lan
        71|Lu|Lutetium|16|9|lan
        72|Hf|Hafnium|4|6|transition
        73|Ta|Tantalum|5|6|transition
        74|W|Tungsten|6|6|transition
        75|Re|Rhenium|7|6|transition
        76|Os|Osmium|8|6|transition
        77|Ir|Iridium|9|6|transition
        78|Pt|Platinum|10|6|transition
        79|Au|Gold|11|6|transition
        80|Hg|Mercury|12|6|transition
        81|Tl|Thallium|13|6|post
        82|Pb|Lead|14|6|post
        83|Bi|Bismuth|15|6|post
        84|Po|Polonium|16|6|post
        85|At|Astatine|17|6|halogen
        86|Rn|Radon|18|6|noble
        87|Fr|Francium|1|7|alkali
        88|Ra|Radium|2|7|alkearth
        89|Ac|Actinium|3|7|act
        90|Th|Thorium|3|10|act
        91|Pa|Protactinium|4|10|act
        92|U|Uranium|5|10|act
        93|Np|Neptunium|6|10|act
        94|Pu|Plutonium|7|10|act
        95|Am|Americium|8|10|act
        96|Cm|Curium|9|10|act
        97|Bk|Berkelium|10|10|act
        98|Cf|Californium|11|10|act
        99|Es|Einsteinium|12|10|act
        100|Fm|Fermium|13|10|act
        101|Md|Mendelevium|14|10|act
        102|No|Nobelium|15|10|act
        103|Lr|Lawrencium|16|10|act
        104|Rf|Rutherfordium|4|7|transition
        105|Db|Dubnium|5|7|transition
        106|Sg|Seaborgium|6|7|transition
        107|Bh|Bohrium|7|7|transition
        108|Hs|Hassium|8|7|transition
        109|Mt|Meitnerium|9|7|transition
        110|Ds|Darmstadtium|10|7|transition
        111|Rg|Roentgenium|11|7|transition
        112|Cn|Copernicium|12|7|transition
        113|Nh|Nihonium|13|7|post
        114|Fl|Flerovium|14|7|post
        115|Mc|Moscovium|15|7|post
        116|Lv|Livermorium|16|7|post
        117|Ts|Tennessine|17|7|halogen
        118|Og|Oganesson|18|7|noble
        """;

    public static String getELEMENT_DATA() {
        return ELEMENT_DATA;
    }

    private final List<Element> elements = new ArrayList<>();
    private final Random random = new Random();
    private final JFrame frame = new JFrame("Elemental - Periodic Table Quiz");
    private final CardLayout cards = new CardLayout();
    private final JPanel screens = new JPanel(cards);
    private final JLabel scoreLabel = new JLabel("0 pts");
    private final JLabel questionScoreLabel = new JLabel("0 pts", SwingConstants.RIGHT);
    private final JLabel questionPromptLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel questionFeedbackLabel = new JLabel("", SwingConstants.CENTER);
    private final JPanel questionOptionsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    private final JPanel questionActionsPanel = new JPanel();
    private final Path leaderboardFile = Paths.get(
        System.getProperty("user.home"), "periodic-table-leaderboard.txt");
    private String playerName = "";
    private int score;
    private boolean scoreSaved;
    private Question currentQuestion;
    private boolean questionAnswered;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PeriodicTable().show());
    }

    private PeriodicTable() {
        loadElements();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setSize(1180, 760);
        frame.setLocationRelativeTo(null);
        screens.add(createStartScreen(), "start");
        screens.add(createQuestionScreen(), "question");
        frame.setContentPane(screens);
    }

    private void show() {
        frame.setVisible(true);
    }

    private void loadElements() {
        String[] rows = ELEMENT_DATA.split("\\R");
        for (String row : rows) {
            if (row.trim().isEmpty()) continue;
            String[] fields = row.split("\\|");
            if (fields.length < 6) continue;
            for (int i = 0; i < fields.length; i++) {
                fields[i] = fields[i].trim();
            }
            elements.add(new Element(
                Integer.parseInt(fields[0]), fields[1], fields[2],
                Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), fields[5]));
        }
    }

    private JPanel createStartScreen() {
        JPanel page = new JPanel(new GridLayout(1, 1));
        page.setBackground(BACKGROUND);
        JPanel card = new JPanel(new GridLayout(0, 1, 8, 8));
        card.setBackground(PANEL);
        card.setBorder(BorderFactory.createEmptyBorder(28, 32, 32, 32));
        card.add(label("Elemental", 30, TEXT, SwingConstants.CENTER));
        card.add(label("Click an element, answer a question, and earn points.", 15,
            MUTED, SwingConstants.CENTER));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        nameField.setToolTipText("Enter your name");
        JButton start = button("Start playing");
        start.addActionListener((ActionEvent event) -> startGame(nameField.getText()));
        nameField.addActionListener((ActionEvent event) -> startGame(nameField.getText()));
        card.add(nameField);
        card.add(start);
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(BACKGROUND);
        holder.add(card, BorderLayout.CENTER);
        holder.setBorder(BorderFactory.createEmptyBorder(110, 220, 110, 220));
        page.add(holder);
        return page;
    }

    private void startGame(String name) {
        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter your name to begin.",
                "Name required", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        playerName = name.trim();
        score = 0;
        scoreSaved = false;
        scoreLabel.setText("0 pts");
        questionScoreLabel.setText("0 pts");
        questionAnswered = false;
        screens.add(createGameScreen(), "game");
        cards.show(screens, "game");
        screens.revalidate();
        screens.repaint();
    }

    private JPanel createGameScreen() {
        JPanel page = new JPanel(new BorderLayout(10, 12));
        page.setBackground(BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel hud = new JPanel(new BorderLayout(10, 0));
        hud.setOpaque(false);
        hud.add(label("Playing as " + playerName, 17, TEXT, SwingConstants.LEFT),
            BorderLayout.WEST);
        scoreLabel.setForeground(ACCENT);
        scoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        hud.add(scoreLabel, BorderLayout.CENTER);
        JButton finish = button("Finish & view leaderboard");
        finish.addActionListener((ActionEvent event) -> showLeaderboard());
        hud.add(finish, BorderLayout.EAST);
        page.add(hud, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(9, 18, 3, 3));
        table.setBackground(BACKGROUND);
        for (int row = 1; row == 9; row++) {
            for (int group = 1; group <= 18; group++) {
                Element element = findElement(row, group);
                if (row == 8 || row == 9) {
                    int seriesNumber = row == 8 ? 58 + group - 3 : 90 + group - 3;
                    element = group >= 3 && group <= 16 ? findByNumber(seriesNumber) : null;
                }
                table.add(element == null ? blankCell() : elementButton(element));
            }
        }
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(BACKGROUND);
        page.add(scroll, BorderLayout.CENTER);
        page.add(label("Click any element to get a question.", 13, MUTED,
            SwingConstants.CENTER), BorderLayout.SOUTH);
        return page;
    }

    private Element findElement(int period, int group) {
        for (Element element : elements) {
            if (element.period == period && element.group == group) return element;
        }
        return null;
    }

    private Element findByNumber(int number) {
        for (Element element : elements) {
            if (element.number == number) return element;
        }
        return null;
    }

    private JButton blankCell() {
        JButton blank = new JButton();
        blank.setEnabled(false);
        blank.setBorderPainted(false);
        blank.setContentAreaFilled(false);
        return blank;
    }

    private JButton elementButton(final Element element) {
        JButton tile = new JButton("?");
        tile.setForeground(TEXT);
        tile.setBackground(PANEL);
        tile.setOpaque(true);
        tile.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        tile.setMargin(new java.awt.Insets(2, 1, 2, 1));
        tile.setToolTipText("Click a hidden element to answer a question");
        tile.getAccessibleContext().setAccessibleName("Hidden element");
        tile.addActionListener((ActionEvent event) -> askQuestion(element));
        return tile;
    }

    private void askQuestion(Element element) {
        currentQuestion = createQuestion(element);
        questionAnswered = false;
        questionPromptLabel.setText("<html><div style='text-align:center;width:560px;'>"
            + escapeHtml(currentQuestion.prompt) + "</div></html>");
        questionFeedbackLabel.setText(" ");
        questionFeedbackLabel.setForeground(TEXT);
        questionOptionsPanel.removeAll();
        for (final String choice : currentQuestion.choices) {
            JButton option = button(choice);
            option.addActionListener((ActionEvent event) -> submitAnswer(choice));
            questionOptionsPanel.add(option);
        }
        questionOptionsPanel.setVisible(false);
        questionOptionsPanel.setVisible(true);
        cards.show(screens, "question");
        screens.revalidate();
        screens.repaint();
    }

    private Question createQuestion(Element element) {
        String prompt;
        String answer;
        List<String> choices = new ArrayList<>();
        int questionType = random.nextInt(5);
        answer = element.name;

        switch (questionType) {
            case 0 -> prompt = "Which element has atomic number " + element.number + "?";
            case 1 -> prompt = "Which element is in group " + element.group + ", period "
                    + displayPeriod(element) + " of the periodic table?";
            case 2 -> {
                prompt = "Which element is both a " + categoryName(element.category)
                        + " and in period " + displayPeriod(element) + "?";
                List<Element> matching = new ArrayList<>();
                for (Element candidate : elements) {
                    if (candidate.number != element.number
                            && candidate.category.equals(element.category)
                            && displayPeriod(candidate) == displayPeriod(element)) {
                        matching.add(candidate);
                    }
                }
                Collections.shuffle(matching);
                for (int i = 0; i < Math.min(3, matching.size()); i++) {
                    choices.add(matching.get(i).name);
                }
            }
            case 3 -> prompt = "A neutral atom has " + element.number
                    + " protons. Which element is it?";
            default -> prompt = "Which element has atomic number " + element.number
                    + " and belongs to the " + categoryName(element.category)
                    + " family?";
        }

        List<Element> distractors = randomElements(element, 3);
        for (Element distractor : distractors) {
            if (!choices.contains(distractor.name)) choices.add(distractor.name);
        }
        while (choices.size() > 3) choices.remove(random.nextInt(choices.size()));

        choices.add(answer);
        Collections.shuffle(choices);
        return new Question(prompt, answer, choices);
    }

    private int displayPeriod(Element element) {
        if (element.period == 9) return 6;
        if (element.period == 10) return 7;
        return element.period;
    }

    private JPanel createQuestionScreen() {
        JPanel page = new JPanel(new BorderLayout(20, 20));
        page.setBackground(BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(28, 50, 28, 50));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(label("Answer the question", 22, TEXT, SwingConstants.LEFT),
            BorderLayout.WEST);
        questionScoreLabel.setForeground(ACCENT);
        questionScoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        header.add(questionScoreLabel, BorderLayout.EAST);
        page.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(18, 18));
        content.setOpaque(false);
        questionPromptLabel.setForeground(TEXT);
        questionPromptLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        questionPromptLabel.setBorder(BorderFactory.createEmptyBorder(35, 10, 20, 10));
        content.add(questionPromptLabel, BorderLayout.NORTH);
        questionOptionsPanel.setOpaque(false);
        content.add(questionOptionsPanel, BorderLayout.CENTER);
        questionFeedbackLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        content.add(questionFeedbackLabel, BorderLayout.SOUTH);

        questionActionsPanel.setOpaque(false);
        JButton continueButton = button("Continue playing");
        continueButton.addActionListener((ActionEvent event) -> {
            cards.show(screens, "game");
            screens.revalidate();
            screens.repaint();
        });
        JButton finishButton = button("Finish & view leaderboard");
        finishButton.addActionListener((ActionEvent event) -> showLeaderboard());
        questionActionsPanel.add(continueButton);
        questionActionsPanel.add(finishButton);
        page.add(content, BorderLayout.CENTER);
        page.add(questionActionsPanel, BorderLayout.SOUTH);
        return page;
    }

    private void submitAnswer(String selected) {
        if (questionAnswered) return;
        questionAnswered = true;
        questionOptionsPanel.setVisible(false);
        questionActionsPanel.setVisible(true);
        if (currentQuestion.answer.equals(selected)) {
            score += 10;
            scoreLabel.setText(score + " pts");
            questionScoreLabel.setText(score + " pts");
            questionFeedbackLabel.setForeground(new Color(74, 222, 128));
            questionFeedbackLabel.setText("Correct! +10 points");
        } else {
            questionFeedbackLabel.setForeground(new Color(248, 115, 92));
            questionFeedbackLabel.setText("<html>Not quite. The correct answer is: "
                + escapeHtml(currentQuestion.answer) + "</html>");
        }
        screens.revalidate();
        screens.repaint();
    }

    private List<Element> randomElements(Element excluded, int count) {
        List<Element> pool = new ArrayList<>();
        for (Element element : elements) {
            if (element.number != excluded.number) pool.add(element);
        }
        Collections.shuffle(pool);
        return pool.subList(0, count);
    }

    private String categoryName(String category) {
        if ("nonmetal".equals(category)) return "Nonmetal";
        if ("noble".equals(category)) return "Noble gas";
        if ("alkali".equals(category)) return "Alkali metal";
        if ("alkearth".equals(category)) return "Alkaline earth metal";
        if ("transition".equals(category)) return "Transition metal";
        if ("post".equals(category)) return "Post-transition metal";
        if ("metalloid".equals(category)) return "Metalloid";
        if ("halogen".equals(category)) return "Halogen";
        if ("lan".equals(category)) return "Lanthanide";
        return "Actinide";
    }

    private void showLeaderboard() {
        List<ScoreEntry> scores = new ArrayList<>();
        String storageMessage = "";
        try {
            if (!scoreSaved) {
                scores = loadScores();
                scores.add(new ScoreEntry(playerName, score));
                saveScores(scores);
                scoreSaved = true;
            } else {
                scores = loadScores();
            }
        } catch (IOException exception) {
            storageMessage = "\n\nThe leaderboard could not be saved or loaded:\n"
                + exception.getMessage();
            System.err.println("Could not access the periodic table leaderboard: " + exception);
            scores.add(new ScoreEntry(playerName, score));
        }

        scores.sort(Comparator.comparingInt((ScoreEntry entry) -> entry.points).reversed());
        StringBuilder text = new StringBuilder();
        text.append(playerName).append(", you finished with ").append(score).append(" points.\n\n");
        text.append(" #   Name                          Score\n");
        text.append("\n");
        int shown = Math.min(scores.size(), 15);
        for (int i = 0; i < shown; i++) {
            ScoreEntry entry = scores.get(i);
            text.append(String.format("%2d   %-28s %5d%n",
                i + 1, entry.name, entry.points));
        }
        if (shown == 0) text.append("No scores yet.\n");
        text.append(storageMessage);

        JPanel page = new JPanel(new BorderLayout(10, 10));
        page.setBackground(BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(35, 60, 35, 60));
        page.add(label("Leaderboard", 28, TEXT, SwingConstants.CENTER), BorderLayout.NORTH);
        JLabel results = label("<html><pre>" + escapeHtml(text.toString()).replace("\n", "<br>")
            + "</pre></html>", 14, TEXT, SwingConstants.CENTER);
        page.add(results, BorderLayout.CENTER);
        JButton playAgain = button("Play again");
        playAgain.addActionListener((ActionEvent event) -> {
            screens.add(createStartScreen(), "start");
            cards.show(screens, "start");
        });
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(playAgain);
        page.add(bottom, BorderLayout.SOUTH);
        screens.add(page, "leaderboard");
        cards.show(screens, "leaderboard");
        screens.revalidate();
        screens.repaint();
    }

    private List<ScoreEntry> loadScores() throws IOException {
        List<ScoreEntry> scores = new ArrayList<>();
        if (!Files.exists(leaderboardFile)) return scores;
        for (String line : Files.readAllLines(leaderboardFile, StandardCharsets.UTF_8)) {
            if (line.trim().isEmpty()) continue;
            String[] fields = line.split("\\|", 2);
            if (fields.length != 2) {
                throw new IOException("Invalid data in " + leaderboardFile);
            }
            try {
                String name = new String(Base64.getDecoder().decode(fields[0]),
                    StandardCharsets.UTF_8);
                scores.add(new ScoreEntry(name, Integer.parseInt(fields[1])));
            } catch (IllegalArgumentException exception) {
                throw new IOException("Invalid data in " + leaderboardFile, exception);
            }
        }
        return scores;
    }

    private void saveScores(List<ScoreEntry> scores) throws IOException {
        List<String> lines = new ArrayList<>();
        for (ScoreEntry entry : scores) {
            String encodedName = Base64.getEncoder().encodeToString(
                entry.name.getBytes(StandardCharsets.UTF_8));
            lines.add(encodedName + "|" + entry.points);
        }
        Files.write(leaderboardFile, lines, StandardCharsets.UTF_8);
    }

    private JLabel label(String text, int size, Color color, int alignment) {
        JLabel label = new JLabel(text, alignment);
        label.setForeground(color);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
        return label;
    }

    private JButton button(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        button.setFocusPainted(false);
        return button;
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static class Element {
        final int number;
        final String name;
        final int group;
        final int period;
        final String category;

        Element(int number, String symbol, String name, int group, int period, String category) {
            this.number = number;
            this.name = name;
            this.group = group;
            this.period = period;
            this.category = category;
        }
    }

    private static class Question {
        final String prompt;
        final String answer;
        final List<String> choices;

        Question(String prompt, String answer, List<String> choices) {
            this.prompt = prompt;
            this.answer = answer;
            this.choices = choices;
        }
    }

    private static class ScoreEntry {
        final String name;
        final int points;

        ScoreEntry(String name, int points) {
            this.name = name;
            this.points = points;
        }
    }
}
