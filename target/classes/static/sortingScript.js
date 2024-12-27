let isSorting = false;  // Flag to check if sorting is currently happening
let currentStep = 0;    // Track the current step
let interval;           // Reference to the interval for controlling animation

function visualizeSorting(steps) {
    if (isSorting) {
        clearInterval(interval);  // Stop the previous sorting
        currentStep = 0;           // Reset the current step
        clearBars();               // Clear the bars before starting new sorting
    }

    isSorting = true;  // Indicate that sorting is happening
    interval = setInterval(() => {
        if (currentStep < steps.length) {
            draw(steps[currentStep]);  // Draw the current step
            currentStep++;
        } else {
            clearInterval(interval);  // Stop once all steps are rendered
            isSorting = false;  // Indicate that sorting has finished
        }
    }, 300);  // Adjust the time delay (100ms here for faster rendering)
}

function clearBars() {
    const barsContainer = document.getElementById('barsContainer');
    barsContainer.innerHTML = '';  // Clear the previous bars
}

function draw(array) {
    const barsContainer = document.getElementById('barsContainer');
    barsContainer.innerHTML = '';  // Clear previous bars before redrawing

    // Create a div for each bar in the array
    array.forEach(value => {
        const bar = document.createElement('div');
        bar.classList.add('bar');  // Add the bar class for styling

        // Set the height and background color of the bar based on the value
        bar.style.height = `${value}px`;
        bar.style.backgroundColor = '#3498db';  // Color of the bars

        // Append the bar to the bars container
        barsContainer.appendChild(bar);
    });
}

function fetchSortingSteps(algorithm) {
    fetch(`/sortingSteps?algorithm=${algorithm}`)
        .then(response => response.json())
        .then(steps => {
            visualizeSorting(steps);  // Call the visualization function with the steps
        })
        .catch(error => {
            console.error('Error fetching sorting steps:', error);
        });
}

document.querySelector('#bubbleSortButton').addEventListener('click', () => {
    fetchSortingSteps('bubble');
});

document.querySelector('#insertionSortButton').addEventListener('click', () => {
    fetchSortingSteps('insertion');
});

document.querySelector('#mergeSortButton').addEventListener('click', () => {
    fetchSortingSteps('merge');
});
