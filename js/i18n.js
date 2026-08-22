const supportedLanguages = ["en", "fr", "de"];

async function loadTranslations(language) {
    if (!supportedLanguages.includes(language)) {
        language = "en";
    }

    const response = await fetch(`i18n/${language}.json`);

    if (!response.ok) {
        throw new Error(`Could not load ${language}.json`);
    }

    return await response.json();
}

async function setLanguage(language) {
    console.log(`Using language: ${language}`);
    const translations = await loadTranslations(language);

    document.querySelectorAll("[data-i18n]").forEach(element => {
        const key = element.dataset.i18n;

        if (translations[key] !== undefined) {
            element.textContent = translations[key];
        }
    });

    localStorage.setItem("language", language);

    document.documentElement.lang = language;
}

const savedLanguage = localStorage.getItem("language");

const browserLanguage = navigator.language
    .split("-")[0];

const language =
    (supportedLanguages.includes((savedLanguage || browserLanguage))
        ? (savedLanguage || browserLanguage)
        : "en");

setLanguage(language);