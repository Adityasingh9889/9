# Daily Tech Automation 🤖

A low-noise GitHub automation for publishing **one useful tech post only when there is meaningful content available**.

## What it does
- Uses a local content queue — no AI API required.
- Publishes at most one post per day.
- Skips empty days instead of creating junk commits.
- Prevents duplicate topics.
- Keeps a history of published posts.
- Runs automatically through GitHub Actions.

## Important
GitHub Actions itself needs an internet-connected runner to push changes to GitHub. The **content source can be completely local/offline**; this project does not call an AI service or news API.

To make it truly offline on your own PC, run:
`python daily-tech-automation/poster.py --local`

Then push the generated file whenever you have internet.
