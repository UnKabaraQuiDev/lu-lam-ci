from pathlib import Path
import json
import re
import subprocess
import sys


# ============================================================
# Configuration
# ============================================================

PLACEHOLDER = "<translation missing>"

I18N_PATTERN = re.compile(
    r'data-i18n\s*=\s*["\']([^"\']+)["\']',
    re.IGNORECASE,
)


# ============================================================
# Git
# ============================================================

def get_repo_root():
    return Path(
        subprocess.check_output(
            ["git", "-C", str(Path(__file__).resolve().parent), "rev-parse", "--show-toplevel"],
            text=True,
        ).strip()
    )


def get_current_branch():
    return subprocess.check_output(
        ["git", "-C", str(Path(__file__).resolve().parent), "branch", "--show-current"],
        text=True,
    ).strip()


# ============================================================
# HTML
# ============================================================

def get_i18n_keys(html_file):
    """Return all unique i18n-data keys from an HTML file."""

    content = html_file.read_text(encoding="utf-8")

    return set(I18N_PATTERN.findall(content))


# ============================================================
# JSON
# ============================================================

def load_json(json_file):
    """Load a translation JSON file."""

    try:
        with json_file.open("r", encoding="utf-8") as file:
            data = json.load(file)

    except json.JSONDecodeError as error:
        print(f"[ERROR] Invalid JSON: {json_file}")
        print(f"        {error}")
        return None

    if not isinstance(data, dict):
        print(f"[ERROR] Expected an object in: {json_file}")
        return None

    return data


def update_translation_file(json_file, required_keys):
    """
    Add missing translation keys to a JSON file.

    Returns the number of keys added.
    """

    data = load_json(json_file)

    if data is None:
        return 0

    missing = sorted(
        key
        for key in required_keys
        if key not in data
    )

    if not missing:
        return 0

    print(f"[UPDATE] {json_file.relative_to(ROOT_DIR)}")

    for key in missing:
        print(f"         + {key}")
        data[key] = PLACEHOLDER

    with json_file.open(
        "w",
        encoding="utf-8",
        newline="\n",
    ) as file:
        json.dump(
            data,
            file,
            ensure_ascii=False,
            indent=4,
        )
        file.write("\n")

    return len(missing)


# ============================================================
# Main
# ============================================================

def main():

    global ROOT_DIR

    ROOT_DIR = get_repo_root()

    branch = get_current_branch()

    if branch != "pages":
        print(f"[ERROR] Current branch is '{branch}', not 'pages'.")
        print("       Checkout the pages branch before running this script.")
        sys.exit(1)

    print(f"Repository: {ROOT_DIR}")
    print(f"Branch:     {branch}")
    print()

    html_files = sorted(ROOT_DIR.rglob("*.html"))

    # Don't accidentally scan generated files or dependencies.
    html_files = [
        path
        for path in html_files
        if ".git" not in path.parts
    ]

    print(f"Found {len(html_files)} HTML files.")
    print()

    # --------------------------------------------------------
    # Collect keys per HTML directory
    # --------------------------------------------------------

    keys_by_directory = {}

    for html_file in html_files:

        keys = get_i18n_keys(html_file)

        if not keys:
            continue

        directory = html_file.parent

        if directory not in keys_by_directory:
            keys_by_directory[directory] = set()

        keys_by_directory[directory].update(keys)

        print(
            f"[SCAN] {html_file.relative_to(ROOT_DIR)} "
            f"({len(keys)} keys)"
        )

    print()

    # --------------------------------------------------------
    # Update translation files
    # --------------------------------------------------------

    total_missing = 0
    total_files = 0

    for directory, required_keys in sorted(
        keys_by_directory.items()
    ):

        i18n_directory = directory / "i18n"

        if not i18n_directory.exists():
            print(
                f"[ERROR] Missing i18n directory: "
                f"{i18n_directory.relative_to(ROOT_DIR)}"
            )
            continue

        json_files = sorted(
            i18n_directory.glob("*.json")
        )

        if not json_files:
            print(
                f"[ERROR] No translation files in: "
                f"{i18n_directory.relative_to(ROOT_DIR)}"
            )
            continue

        for json_file in json_files:

            data = load_json(json_file)

            if data is None:
                continue

            missing = sorted(
                key
                for key in required_keys
                if key not in data
            )

            if not missing:
                continue

            print(
                f"[MISSING] "
                f"{json_file.relative_to(ROOT_DIR)}"
            )

            for key in missing:
                print(f"          {key}")

            # Add placeholders
            for key in missing:
                data[key] = PLACEHOLDER

            with json_file.open(
                "w",
                encoding="utf-8",
                newline="\n",
            ) as file:
                json.dump(
                    data,
                    file,
                    ensure_ascii=False,
                    indent=4,
                )
                file.write("\n")

            total_missing += len(missing)
            total_files += 1

            print(
                f"          Added {len(missing)} "
                f"placeholder(s)."
            )
            print()

    # --------------------------------------------------------
    # Summary
    # --------------------------------------------------------

    print("Done.")
    print(f"HTML directories scanned: {len(keys_by_directory)}")
    print(f"Translation files updated: {total_files}")
    print(f"Missing translations added: {total_missing}")

    # Missing keys were found and placeholders were added.
    # Return non-zero so CI reports that translations are missing.
    if total_missing > 0:
        print()
        print(
            f"[ERROR] {total_missing} missing translation(s) "
            "were added as placeholders."
        )
        sys.exit(1)


if __name__ == "__main__":
    main()