package com.thoughtworks.trains.model;

/**
 * Model class representing an edge.
 */
public class Edge {

    private Town startTown;
    private Town endTown;

    public Edge(Town startTown, Town endTown) {
        super();
        this.startTown = startTown;
        this.endTown = endTown;
    }

    public Town getStartTown() {
        return startTown;
    }

    public void setStartTown(Town startTown) {
        this.startTown = startTown;
    }

    public Town getEndTown() {
        return endTown;
    }

    public void setEndTown(Town endTown) {
        this.endTown = endTown;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endTown == null) ? 0 : endTown.hashCode());
        result = prime * result + ((startTown == null) ? 0 : startTown.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Edge other = (Edge) obj;
        if (endTown == null) {
            if (other.endTown != null) {
                return false;
            }
        } else if (!endTown.equals(other.endTown)) {
            return false;
        }
        if (startTown == null) {
            if (other.startTown != null) {
                return false;
            }
        } else if (!startTown.equals(other.startTown)) {
            return false;
        }
        return true;
    }

}
