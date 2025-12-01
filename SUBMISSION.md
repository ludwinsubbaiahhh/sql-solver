# Submission Instructions

## If JAR file shows 404:

The JAR file needs to be built and uploaded. Here are your options:

### Option 1: Build using IntelliJ IDEA (Easiest)

1. Open project in IntelliJ IDEA
2. Right-click `pom.xml` → `Maven` → `Reload Project`
3. In Maven tool window, expand `Lifecycle` → double-click `package`
4. Find JAR at: `target/webhook-sql-solver-1.0.0.jar`
5. Copy it to `releases/webhook-sql-solver-1.0.0.jar`
6. Commit and push:
   ```bash
   git add releases/webhook-sql-solver-1.0.0.jar
   git commit -m "Add JAR file"
   git push origin main
   ```

### Option 2: Use GitHub Releases (Alternative)

1. Build JAR using any IDE
2. Go to GitHub → Your repo → Releases → Create new release
3. Tag: `v1.0.0`, Title: `v1.0.0`
4. Upload the JAR file as an asset
5. Use this download link: `https://github.com/ludwinsubbaiahhh/sql-solver/releases/download/v1.0.0/webhook-sql-solver-1.0.0.jar`

### Option 3: Wait for GitHub Actions

The workflow should auto-build, but you may need to:
1. Go to GitHub repo → Actions tab
2. Run the workflow manually if needed
3. Wait for it to complete

