package ru.svlit.core.globalization;

/**
 * Текстовый ресурс.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public interface Resource {
    String getCode();

    class TitleResource implements Resource {

        @Override
        public String getCode() {
            return "navigation.title";
        }
    }

    class FeaturesResource implements Resource {
        @Override
        public String getCode() {
            return "navigation.features";
        }
    }
    class ContactsResource implements Resource {
        @Override
        public String getCode() {
            return "navigation.contacts";
        }
    }
    class SignInResource implements Resource {
        @Override
        public String getCode() {
            return "navigation.signIn";
        }
    }
    class SignOutResource implements Resource {
        @Override
        public String getCode() {
            return "navigation.signOut";
        }
    }
    class SignUpResource implements Resource {
        @Override
        public String getCode() {
            return "navigation.signUp";
        }
    }

    class FeatureWallTitleResource implements Resource {
        @Override
        public String getCode() {
            return "feature.wall.title";
        }
    }

    class FeatureCovidTitleResource implements Resource {
        @Override
        public String getCode() {
            return "feature.covid.title";
        }
    }

    class FeatureAdminPanelTitleResource implements Resource {
        @Override
        public String getCode() {
            return "feature.admin-panel.title";
        }
    }

    class LanguageRussianResource implements Resource {

        @Override
        public String getCode() {
            return "language.ru";
        }
    }

    class LanguageEnglishResource implements Resource {

        @Override
        public String getCode() {
            return "language.en";
        }
    }
}
