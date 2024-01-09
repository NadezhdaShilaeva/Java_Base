package com.shilaeva.oop;

public class LabelsCheck {
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer: analyzers) {
            Label result = analyzer.processText(text);
            if (!result.equals(Label.OK)) {
                return result;
            }
        }

        return Label.OK;
    }

    abstract class KeywordAnalyzer implements TextAnalyzer {
        protected abstract String[] getKeywords();
        protected abstract Label getLabel();

        public Label processText(String text) {
            for (String keyword: getKeywords()) {
                if (text.contains(keyword)) {
                    return getLabel();
                }
            }

            return Label.OK;
        }
    }

    class SpamAnalyzer extends KeywordAnalyzer {
        private final String[] keywords;

        public SpamAnalyzer(String[] keywords) {
            this.keywords = keywords;
        }

        @Override
        protected String[] getKeywords() {
            return keywords;
        }

        @Override
        protected Label getLabel() {
            return Label.SPAM;
        }
    }

    class NegativeTextAnalyzer extends KeywordAnalyzer {
        private final String[] keywords = {":(", "=(", ":|"};

        @Override
        protected String[] getKeywords() {
            return keywords;
        }

        @Override
        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    class TooLongTextAnalyzer implements TextAnalyzer {
        private final int maxLength;

        public TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String text) {
            return text.length() <= maxLength ? Label.OK : Label.TOO_LONG;
        }
    }
}
