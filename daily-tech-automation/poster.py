import argparse
import json
from datetime import datetime, timezone
from pathlib import Path

ROOT = Path(__file__).parent
QUEUE = ROOT / "posts.json"
STATE = ROOT / "state.json"
OUT = ROOT / "published"

def load(path):
    return json.loads(path.read_text(encoding="utf-8"))

def save(path, data):
    path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")

def publish_one():
    queue = load(QUEUE)
    state = load(STATE)
    published = set(state.get("published_ids", []))

    available = [p for p in queue["posts"] if p["id"] not in published]

    # No meaningful unused content = do nothing.
    if not available:
        print("SKIP: no new meaningful post is available.")
        return False

    post = available[0]
    now = datetime.now(timezone.utc)
    filename = OUT / f"{now:%Y-%m-%d}-{post['id']}.md"
    OUT.mkdir(exist_ok=True)

    content = f"""# {post['title']}

{post['body']}

**Topics:** {" ".join("#" + x for x in post["tags"])}

*Published by Daily Tech Automation on {now:%Y-%m-%d}.*
"""
    filename.write_text(content, encoding="utf-8")
    state["published_ids"].append(post["id"])
    save(STATE, state)
    print(f"PUBLISHED: {filename}")
    return True

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--local", action="store_true", help="Generate locally without GitHub Actions.")
    parser.parse_args()
    publish_one()
