function openCreateModal() {
  document.getElementById("createModal").style.display = "flex";
}

function closeCreateModal() {
  document.getElementById("createModal").style.display = "none";
}

async function createTask() {
  const task = {
    title: document.getElementById("createTitle").value,
    description: document.getElementById("createDescription").value,
    state: document.getElementById("createState").value,
    dateCreation: new Date().toISOString().split("T")[0],
  };

  const response = await fetch("/api/tasks", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(task),
  });

  const result = await response.json();

  alert(result.message);

  closeCreateModal();

  location.reload();
}

async function searchTask() {
  const id = document.getElementById("taskId").value;

  if (!id) {
    alert("Ingrese un ID");
    return;
  }

  const response = await fetch(`/api/tasks/${id}`);

  if (!response.ok) {
    alert("Tarea no encontrada");
    return;
  }

  const task = await response.json();

  alert(
    `ID: ${task.id}
Título: ${task.title}
Descripción: ${task.description}
Estado: ${task.state}`,
  );
}

async function deleteTask(id) {
  const confirmDelete = confirm("¿Desea eliminar esta tarea?");

  if (!confirmDelete) {
    return;
  }

  const response = await fetch(`/api/tasks/${id}`, {
    method: "DELETE",
  });

  const result = await response.json();

  alert(result.message);

  location.reload();
}

async function editTask(id) {
  const response = await fetch(`/api/tasks/${id}`);

  if (!response.ok) {
    alert("No se encontró la tarea");
    return;
  }

  const task = await response.json();

  document.getElementById("editId").value = task.id;

  document.getElementById("editTitle").value = task.title;

  document.getElementById("editDescription").value = task.description;

  document.getElementById("editState").value = task.state;

  document.getElementById("editModal").style.display = "flex";
}

function closeModal() {
  document.getElementById("editModal").style.display = "none";
}

async function updateTask() {
  const id = document.getElementById("editId").value;

  const task = {
    title: document.getElementById("editTitle").value,

    description: document.getElementById("editDescription").value,

    state: document.getElementById("editState").value,
  };

  const response = await fetch(`/api/tasks/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(task),
  });

  const result = await response.json();

  alert(result.message);

  closeModal();

  location.reload();

  async function searchTask() {
    const id = document.getElementById("taskId").value;

    if (!id) {
      location.reload();
      return;
    }

    const response = await fetch(`/api/tasks/${id}`);

    if (!response.ok) {
      alert("Tarea no encontrada");
      return;
    }

    const task = await response.json();

    const tbody = document.querySelector("tbody");

    tbody.innerHTML = `
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.dateCreation}</td>
            <td>${task.state}</td>
            <td>
                <button
                    class="btn-edit"
                    onclick="editTask(${task.id})">
                    Editar
                </button>

                <button
                    class="btn-delete"
                    onclick="deleteTask(${task.id})">
                    Eliminar
                </button>
            </td>
        </tr>
    `;
    function filterByState() {
      const state = document.getElementById("stateFilter").value;

      const rows = document.querySelectorAll("tbody tr");

      rows.forEach((row) => {
        const taskState = row.children[4].textContent.trim();

        if (state === "Todos") {
          row.style.display = "";
        } else if (taskState === state) {
          row.style.display = "";
        } else {
          row.style.display = "none";
        }
      });
    }
  }
}
