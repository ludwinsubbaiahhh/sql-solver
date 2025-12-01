# Building JAR in VS Code - Step by Step

## Step 1: Install Java Extension Pack
1. Open VS Code
2. Press `Ctrl+Shift+X` to open Extensions
3. Search for "Extension Pack for Java" (by Microsoft)
4. Click **Install**

## Step 2: Open Project
1. In VS Code: File → Open Folder
2. Select the project folder: `C:\Users\91782\Documents\bajaj finserv`

## Step 3: Build JAR File
1. Wait for VS Code to detect Java project (look for "Java Projects" in sidebar)
2. Click on `pom.xml` file in the explorer
3. You should see Maven options at the top or in the Java Projects view
4. Look for Maven icon or "Maven" section in the sidebar
5. Expand: `webhook-sql-solver` → `Lifecycle`
6. **Right-click on `package`** → Click **Run**
   OR
   Double-click `package`

## Step 4: Find Your JAR
After build completes:
- JAR will be in: `target/webhook-sql-solver-1.0.0.jar`

## Step 5: Copy to Releases Folder
1. In VS Code file explorer, create/copy:
   - Source: `target/webhook-sql-solver-1.0.0.jar`
   - Destination: `releases/webhook-sql-solver-1.0.0.jar`

## Step 6: Commit and Push
Open terminal in VS Code (`Ctrl+`` or Terminal → New Terminal) and run:
```bash
git add releases/webhook-sql-solver-1.0.0.jar
git commit -m "Add JAR file"
git push origin main
```

## Alternative: Using Terminal in VS Code
If Maven menu doesn't appear:
1. Open Terminal in VS Code (`Ctrl+``)
2. Run: `mvn clean package`
3. If Maven not found, use the extension's built-in Maven support

