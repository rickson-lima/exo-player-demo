const express = require("express");
const app = express();
const port = 3000;
const fs = require("node:fs");

app.get("/", (req, res) => {
  // const songs = ["marilia1", "marilia2", "pablo", "eminem"];
  const songs = fs.readdirSync(__dirname + "/playlist/", (err, files) => {
    if (err) {
      console.error("Error reading folder");
      return;
    }

    return files
      .filter((file) => file.statSync("${folderPath}/${files}").isFile())
      .map((file) => file);
  });

  console.info({ songs });

  const randomIndex = Math.floor(Math.random() * songs.length);
  const randomSong = songs[randomIndex];

  const audioPath = __dirname + "/playlist/" + randomSong;
  res.setHeader("Content-Type", "audio/mp3");

  const audioStream = fs.createReadStream(audioPath);

  audioStream.on("error", (err) => {
    console.error(err);
    res.status(500).send("ISE");
  });

  audioStream.pipe(res);
});

app.listen(port, () => {
  console.log(`Example app listening on port http://localhost:${port}`);
});
