#!/bin/bash

# 1. Get the absolute path to THIS folder
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
RUN_SCRIPT="$PROJECT_DIR/run.sh"

# 2. Make the run script executable
chmod +x "$RUN_SCRIPT"

# 3. Detect which shell they use (zsh or bash)
if [ -f "$HOME/.zshrc" ]; then
    CONF_FILE="$HOME/.zshrc"
elif [ -f "$HOME/.bashrc" ]; then
    CONF_FILE="$HOME/.bashrc"
else
    # Default to .zshrc on Mac if neither exists
    CONF_FILE="$HOME/.zshrc"
    touch "$CONF_FILE"
fi

# 4. Add the alias if it's not already there
if grep -q "alias passpaster=" "$CONF_FILE"; then
    echo "Updating existing alias in $CONF_FILE..."
    # Use a different delimiter (|) because paths have slashes
    sed -i '' "s|alias passpaster=.*|alias passpaster='$RUN_SCRIPT'|" "$CONF_FILE"
else
    echo "Adding alias 'passpaster' to $CONF_FILE..."
    echo "" >> "$CONF_FILE"
    echo "# Password Paster Alias" >> "$CONF_FILE"
    echo "alias passpaster='$RUN_SCRIPT'" >> "$CONF_FILE"
fi

echo "------------------------------------------------"
echo "✅ Installation complete!"
echo "1. Run: source $CONF_FILE"
echo "2. Then type: passpaster"
echo "------------------------------------------------"
