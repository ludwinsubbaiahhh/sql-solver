# How to Build and Upload JAR File

## The Problem
The JAR file is showing 404 because it hasn't been built and committed to the repository yet.

## Quick Solutions

### ✅ **Solution 1: Build with IntelliJ IDEA (Recommended)**

1. **Open IntelliJ IDEA** and open this project folder
2. **Wait for Maven import** - IntelliJ should auto-detect the `pom.xml`
3. **Open Maven tool window** (View → Tool Windows → Maven, or right side panel)
4. **Expand project** → `webhook-sql-solver` → `Lifecycle`
5. **Double-click `package`** - This will build the JAR
6. **Find the JAR**: It will be in `target/webhook-sql-solver-1.0.0.jar`
7. **Copy to releases folder**:
   - Copy `target/webhook-sql-solver-1.0.0.jar`
   - Paste into `releases/webhook-sql-solver-1.0.0.jar`
8. **Commit and push**:
   ```bash
   git add releases/webhook-sql-solver-1.0.0.jar
   git commit -m "Add built JAR file"
   git push origin main
   ```

### ✅ **Solution 2: Build with VS Code**

1. Install **Extension Pack for Java** in VS Code
2. Open the project folder in VS Code
3. Open `pom.xml` - you should see Maven icon
4. Click the Maven icon → Run `package`
5. JAR will be in `target/webhook-sql-solver-1.0.0.jar`
6. Copy to `releases/` folder and commit/push

### ✅ **Solution 3: Manual GitHub Actions Trigger**

1. Go to your GitHub repository: https://github.com/ludwinsubbaiahhh/sql-solver
2. Click **Actions** tab
3. Click **Build and Release JAR** workflow
4. Click **Run workflow** button (right side)
5. Wait for it to complete (usually 2-3 minutes)
6. The JAR should appear in `releases/` folder

### ✅ **Solution 4: Use GitHub Releases**

1. Build JAR using any method above
2. Go to GitHub repo → **Releases** → **Create a new release**
3. Tag: `v1.0.0`
4. Title: `v1.0.0 - Initial Release`
5. **Upload** the JAR file as an asset
6. Use this download link: 
   ```
   https://github.com/ludwinsubbaiahhh/sql-solver/releases/download/v1.0.0/webhook-sql-solver-1.0.0.jar
   ```

## After Building

Once the JAR is uploaded, these links will work:
- **Raw GitHub Link:** `https://raw.githubusercontent.com/ludwinsubbaiahhh/sql-solver/main/releases/webhook-sql-solver-1.0.0.jar`
- **GitHub Releases:** `https://github.com/ludwinsubbaiahhh/sql-solver/releases/download/v1.0.0/webhook-sql-solver-1.0.0.jar`

