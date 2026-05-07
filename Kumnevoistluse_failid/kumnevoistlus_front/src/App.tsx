import { useEffect, useState } from 'react';
import './App.css';
import type Athlete from './models/Athlete';

function App() {
  const [athletes, setAthletes] = useState<Athlete[]>([]);
  const [name, setName] = useState('');

  useEffect(() => {
    fetchAthletes();
  }, []);

  function fetchAthletes() {
    fetch(import.meta.env.VITE_BACK_URL + '/decathlon/athletes')
      .then(response => response.json())
      .then(data => setAthletes(data));
  }

  function addAthlete() {
    fetch(import.meta.env.VITE_BACK_URL + '/decathlon/athletes', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: name
      })
    })
      .then(response => response.json())
      .then(() => {
        setName('');
        fetchAthletes();
      });
  }

  function deleteAthlete(id: number) {
    fetch(import.meta.env.VITE_BACK_URL + '/decathlon/athletes/' + id, {
      method: 'DELETE'
    })
      .then(response => response.json())
      .then(data => setAthletes(data));
  }

  return (
    <>
      <h1>Kümnevõistlus</h1>

      <label>Sportlase nimi</label>
      <br />
      <input
        value={name}
        onChange={(event) => setName(event.target.value)}
      />
      <button onClick={addAthlete}>Lisa sportlane</button>

      <br /><br />

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nimi</th>
            <th>Kustuta</th>
          </tr>
        </thead>

        <tbody>
          {athletes.map(athlete =>
            <tr key={athlete.id}>
              <td>{athlete.id}</td>
              <td>{athlete.name}</td>
              <td>
                <button onClick={() => deleteAthlete(athlete.id)}>
                  x
                </button>
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </>
  );
}

export default App;